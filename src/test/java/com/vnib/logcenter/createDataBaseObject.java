package com.vnib.logcenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createDataBaseObject {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	WebDriverWait waitExplicit;
	WebDriverWait wait;
	
	// Login Data
	String linkServer = "aaaaaaaa";
	String username = "rrrrrrrr";
	String password = "rrrrrrrrrrr";
	String hostIPItem = "9.9.9.9";

	// Altibase Data
	String altibaseObjectName = "Altibase";
	String altibaseURL = "jdbc:Altibase://192.168.100.89:20300/sysdba";
	String altibaseID = "sys";
	String altibasePassword = "manager";
	String altibaseQuerySQL = "SELECT * FROM EMPLOYEE";
	
	//DB2 data
	String db2ObjectName = "DB2";
	String db2URL = "jdbc:db2://192.168.100.89:50000/DBIDM";
	String db2ID = "db2";
	String db2Password = "abcd@54321";
	String db2QuerySQL = "SELECT * FROM EMPLOYEE";
	
	// Oracle data
	String oracleObjectName = "Oracle";
	String oracleURL = "jdbc:oracle:thin:@192.168.100.87:1521:xe";
	String oracleID = "system";
	String oraclePassword = "oracle";
	String oracleQuerySQL = "SELECT * FROM NHANVIEN";
	
	// Postgresql data
	String postgresqlObjectName = "Postgresql";
	String postgresqlURL = "jdbc:postgresql://192.168.100.89:5432/postgres";
	String postgresqlID = "postgres";
	String postgresqlPassword = "abcd@54321";
	String postgresqlQuerySQL = "SELECT * FROM EMPLOYEE";
	
	//Find Elements
	By usernameTextbox = By.xpath("//input[@id='login-username']");
	By passwordTextbox = By.xpath("//input[@id='login-password']");
	By loginButton = By.xpath("//button[@id='btn-login']");

	By hostInformationButton = By.xpath("//span[@id='btn-route-hosts']");
	By hostNameItemBox = By.xpath("//small[contains(text(),'" + hostIPItem + "')]");

	By connectButton = By.xpath("//button[@class='btn btn--connectthing']//span[@class='text']");
	By goToConnectingButton = By.xpath("//button[contains(text(),'Go to Connecting')]");
	By countObject = By.xpath("//div[@class='fly-box']//ul//li[@class='nav-item object']/a/span[@class='text']");
	By loggingTab = By.xpath("//div[@class='modal-content']//li[@class='nav-item']//a[(text()='Logging')]");
	By databaseObject = By.xpath("//div[@class='tab-content']//div[@class='content']//h3[text()='Database']");
	By connectObjectTitle = By.xpath("/h5[contains(text(),'Connect Object')]");
	By connectDatabaseTitle = By.xpath("//h5[contains(text(),'Connect Database')]");
	By objectNameInputBox = By.xpath("//input[@id='hostinfo_name']");
	By dbTypeSelectBox = By.xpath("//select[@id='dbtype']");
	By dbTypeItemsList = By.xpath("//select[@id='dbtype']//option");
	By dbURLInputBox = By.xpath("//input[@id='dburl']");
	By iDInputBox = By.xpath("//input[@id='usrID']");
	By passWordInputBox = By.xpath("//input[@name='usrpw']");
	By selectSQLTextArea = By.xpath("//textarea[@id='dbsltsql']");
	By rawDataAndIndexedDataRadioButton = By.xpath("//label[contains(text(),'Raw Data & Indexed Data')]");
	By saveButton = By.xpath("//button[contains(text(),'Save')]");
	By showModal=By.xpath("//div[@class='modal-backdrop fade show modal-stack']");
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitExplicit=new WebDriverWait(driver, 5);
		driver.manage().window().maximize();
		driver.get(linkServer);
		String connectXpath = "//h1[contains(text(),'Your connection is not private')]";
		if (driver.findElement(By.xpath(connectXpath)).isDisplayed() == true) {
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@class='small-link']")).click();
		} else {
			System.out.println("");
		}
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		driver.findElement(hostInformationButton).click();
		scrollToElement(hostNameItemBox);
		driver.findElement(hostNameItemBox).click();
		driver.findElement(hostNameItemBox).click();
		driver.findElement(countObject).click();
		driver.findElement(countObject).click();
	}

	@Test
	public void TC_01_Create_Altibase_Database_Object() throws InterruptedException {
		clickToConnectButton();
		createObject("Logging", "Database");
		driver.findElement(objectNameInputBox).sendKeys(altibaseObjectName);
		selectItemInCustomDropdown(dbTypeSelectBox, dbTypeItemsList, "Altibase");
		driver.findElement(dbURLInputBox).clear();
		driver.findElement(dbURLInputBox).sendKeys(altibaseURL);
		driver.findElement(iDInputBox).sendKeys(altibaseID);
		driver.findElement(passWordInputBox).sendKeys(altibasePassword);
		driver.findElement(selectSQLTextArea).clear();
		driver.findElement(selectSQLTextArea).sendKeys(altibaseQuerySQL);
		driver.findElement(rawDataAndIndexedDataRadioButton).click();
		driver.findElement(saveButton).click();
	}

	@Test
	public void TC_02_Create_DB2_Database_Object() throws InterruptedException {
		removeElement(showModal);
		clickToConnectButton();
		createObject("Logging", "Database");
		driver.findElement(objectNameInputBox).sendKeys(db2ObjectName);
		selectItemInCustomDropdown(dbTypeSelectBox, dbTypeItemsList, "DB2");
		driver.findElement(dbURLInputBox).clear();
		driver.findElement(dbURLInputBox).sendKeys(db2URL);
		driver.findElement(iDInputBox).sendKeys(db2ID);
		driver.findElement(passWordInputBox).sendKeys(db2Password);
		driver.findElement(selectSQLTextArea).clear();
		driver.findElement(selectSQLTextArea).sendKeys(db2QuerySQL);
		driver.findElement(rawDataAndIndexedDataRadioButton).click();
		driver.findElement(saveButton).click();
	}

	@Test
	public void TC_03_Create_Oracle_Database_Object() throws InterruptedException {
		removeElement(showModal);
		clickToConnectButton();
		createObject("Logging", "Database");
		driver.findElement(objectNameInputBox).sendKeys(oracleObjectName);
		selectItemInCustomDropdown(dbTypeSelectBox, dbTypeItemsList, "Oracle");
		driver.findElement(dbURLInputBox).clear();
		driver.findElement(dbURLInputBox).sendKeys(oracleURL);
		driver.findElement(iDInputBox).sendKeys(oracleID);
		driver.findElement(passWordInputBox).sendKeys(oraclePassword);
		driver.findElement(selectSQLTextArea).clear();
		driver.findElement(selectSQLTextArea).sendKeys(oracleQuerySQL);
		driver.findElement(rawDataAndIndexedDataRadioButton).click();
		driver.findElement(saveButton).click();
	}

	@Test
	public void TC_04_Create_Postgresql_Database_Object() throws InterruptedException {
		
		//removeElement(showModal);
		removeElement(showModal);
		clickToConnectButton();
		createObject("Logging", "Database");
		driver.findElement(objectNameInputBox).sendKeys(postgresqlObjectName);
		selectItemInCustomDropdown(dbTypeSelectBox, dbTypeItemsList, "Postgresql");
		driver.findElement(dbURLInputBox).clear();
		driver.findElement(dbURLInputBox).sendKeys(postgresqlURL);
		driver.findElement(iDInputBox).sendKeys(postgresqlID);
		driver.findElement(passWordInputBox).sendKeys(postgresqlPassword);
		driver.findElement(selectSQLTextArea).clear();
		driver.findElement(selectSQLTextArea).sendKeys(postgresqlQuerySQL);
		driver.findElement(rawDataAndIndexedDataRadioButton).click();
		driver.findElement(saveButton).click();
	}
	
	
	public void clickToConnectButton() throws InterruptedException {
		
		Thread.sleep(1000);
		if (driver.findElement(countObject).getText().contentEquals("0")==true) {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(goToConnectingButton));
			driver.findElement(goToConnectingButton).click();
		} else{
			//wait.until(ExpectedConditions.visibilityOfElementLocated(connectButton));
			driver.findElement(connectButton).click();
		}

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	String tabNameValue = "LOGGING";

	public void createObject(String tabNameValue, String dataFormatValue) {

		By tabName = By.xpath("//div[@class='modal-content']//li[@class='nav-item']//a[(text()='" + tabNameValue + "')]");
		By dataFormat = By.xpath("//div[@class='tab-content']//div[@class='content']//h3[text()='" + dataFormatValue + "']");
		driver.findElement(tabName).click();
		driver.findElement(dataFormat).click();
	}
	public void selectItemInCustomDropdown(By parentXpath, By allItemsXpath, String expectedText) throws InterruptedException {
		// 01. Click vào thẻ chứa Dropdown để show all items
		// System.out.println("chuan bi wait");
		// waitExplicit.until(ExpectedConditions.elementToBeClickable(parentXpath));
		System.out.println("chua click");
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(parentXpath));
		System.out.println("click roi");

		// 02. Wai để tất cả các item (List WebElement) được xuất hiện trong DOM
//		 System.out.println("waiting");
//		 waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemsXpath));

		// 03. Khai báo 1 List <Element> chứa all items bên trong.
		List<WebElement> allItems = driver.findElements(allItemsXpath);
		// 04. Tổng số lượng item trong 1 dropdown bằng bao nhiêu
		System.out.println("Item size= " + allItems.size());

		// 05. Duyệt qua từng cái item
		for (WebElement item : allItems) {
			System.out.println("Item: " + item.getText() + ".");

			// 05. Kiểm tra item nào đúng với mình cần ch�?n thì click vào
			if (item.getText().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				if (item.isDisplayed()) {
					System.out.println("Item click by Selenium: " + item.getText());
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					Thread.sleep(1000);
					System.out.println("Item click by js: " + item.getText());
					jsExecutor.executeScript("arguments[0].click();", item);
				}
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public void scrollToElement(By xpathElement) {
		element=driver.findElement(xpathElement);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void removeElement(By xpathElement) {
		WebElement elementm=driver.findElement(xpathElement);
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].parentNode.removeChild(arguments[0])", elementm);
	}
}

package com.vnib.logcenter;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateMultipleHost{

	WebDriver driver;
	List<WebElement> elements;
	WebElement element;
	JavascriptExecutor jsExecutor;
	Select select;
	WebDriverWait waitExplicit;

	//Login Data
	String linkServer = "https://aaaaaa/";
	String username = "aaaaaaa";
	String password = "aaaaa";

	// Group Data (Manual Create with Group Name: 1 --> 6 in server)
	String groupName_TC01 = "1";
	String groupName_TC02 = "2";
	String groupName_TC03 = "3";
	String groupName_TC04 = "4";
	String groupName_TC05 = "5";
	String groupName_TC06 = "6";

	//Host ip
	String hostIP_TC01 = "99.99.62.";
	String hostIP_TC02 = "99.100.52.";
	String hostIP_TC03 = "99.101.50.";
	String hostIP_TC04 = "99.102.49.";
	String hostIP_TC05 = "99.103.48.";
	String hostIP_TC06 = "99.104.48.";

	//Timezone name data
	String timeZone="(UTC-10:00) Hawaii";
	
	// Host Name create object
	By usernameTextbox = By.xpath("//input[@id='login-username']");
	By passwordTextbox = By.xpath("//input[@id='login-password']");
	By loginButton = By.xpath("//button[@id='btn-login']");

	By hostInformationButton = By.xpath("//span[@id='btn-route-hosts']");
	By createHostButton = By.xpath("//button[@class='btn btn-link btn-add--host']");

	By logCenterItemHost = By.xpath("//ul//li//p[contains(text(),'LogCenter')]");

	By timezoneButton = By.xpath("//label[contains(text(),'Timezone')]/following-sibling::div//a[@data-toggle='dropdown']");
	By itemsTimezoneList = By.xpath("//label[contains(text(),'Timezone')]//ancestor::form//ancestor::div[@class='tab-content']//ancestor::div[@class='host-tabs']/ancestor::div[@class='modal-content']/ancestor::div[@role='dialog']/following-sibling::div[@role='menu']//a//span");
	By groupButton = By.xpath("//label[contains(text(),'Group')]/following-sibling::div//a[@data-toggle='dropdown']");
	By itemsGroupList = By.xpath("//label[contains(text(),'Group')]//ancestor::form//ancestor::div[@class='tab-content']//ancestor::div[@class='host-tabs']/ancestor::div[@class='modal-content']/ancestor::div[@role='dialog']/following-sibling::div[@role='menu']//a//span");

	By allItem = By.xpath("//div[@class='dropdown-menu lc-dropdown-list top-placement ps ps--active-y show']/a/span");
	By editTopologyButton = By.xpath("//div[@class='ap-tooltip edit']/following-sibling::button");

	By hostNameTextbox = By.xpath("//input[@id='hostName']");
	By hostIPTextbox = By.xpath("//input[@id='hostIp']");
	By saveHostButton = By.xpath("//button[@class='btn btn-loading btn-primary']");
	By cancelHostButton = By.xpath("//div[@role='dialog']//div[@class='modal-dialog modal-dialog-centered']//div[@class='modal-footer']//button[contains(text(),'Cancel')]");

	By undefinedErrorPopup = By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]");
	By okButtonPopup = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Ok')]");

	// Pre-condition
	@BeforeClass
	public void beforeClass() {

		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(linkServer);
		String connectXpath = "//h1[contains(text(),'Your connection is not private')]";
		if (driver.findElement(By.xpath(connectXpath)).isDisplayed()==true) {
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@class='small-link']")).click();
		}
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		driver.findElement(hostInformationButton).click();
	}

	AtomicInteger sequence = new AtomicInteger(0);
	@Test(invocationCount = 25)
	public void TC_01_Create_Host() throws InterruptedException {
		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC01 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC01);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			Thread.sleep(500);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList,timeZone);
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);
			
			System.out.println();
			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isEnabled() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();
			} else {
				System.out.println("Error popup is not displayed");
			}
		} catch (Exception ex) {
			System.out.println("Error tc01: " + ex);
		}
	}

	@Test(invocationCount = 25)
	public void TC_02_Create_Host() throws InterruptedException {

		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC02 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC02);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);
			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isDisplayed() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();

			} else {
				System.out.println("Error popup is displayed");
			}
		} catch (Exception ex) {
			System.out.println("Error tc02: " + ex);
		}
	}

	@Test(invocationCount = 25)
	public void TC_03_Create_Host() throws InterruptedException {

		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC03 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC03);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);
			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isDisplayed() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();

			} else {
				System.out.println("Error popup is displayed");
			}
		} catch (Exception ex) {
			System.out.println("Error tc03: " + ex);
		}
	}

	@Test(invocationCount = 25)
	public void TC_04_Create_Host() throws InterruptedException {

		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC04 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC04);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);

			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isDisplayed() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();

			} else {
				System.out.println("Error popup is displayed");
			}

		} catch (Exception ex) {
			System.out.println("Error tc04: " + ex);
		}
	}

	@Test(invocationCount = 25)
	public void TC_05_Create_Host() throws InterruptedException {

		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC05 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC05);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);

			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isDisplayed() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();

			} else {
				System.out.println("Error popup is displayed");
			}

		} catch (Exception ex) {
			System.out.println("Error tc05: " + ex);
		}
	}

	@Test(invocationCount = 25)
	public void TC_06_Create_Host() throws InterruptedException {

		try {
			int count = sequence.addAndGet(1);
			String hostIP_TC = hostIP_TC06 + count;
			waitToElementClickable(createHostButton);
			driver.findElement(createHostButton).click();
			Thread.sleep(1000);
			selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC06);
			String hostName = hostIP_TC;
			driver.findElement(hostNameTextbox).sendKeys(hostName);
			driver.findElement(hostIPTextbox).sendKeys(hostIP_TC);
			Thread.sleep(500);
			selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
			driver.findElement(saveHostButton).click();
			Thread.sleep(1000);

			if (driver.findElement(By.xpath("//div[@class='modal-header']//h5[contains(text(),'Error')]")).isDisplayed() == true) {
				driver.findElement(okButtonPopup).click();
				driver.findElement(cancelHostButton).click();

			} else {
				System.out.println("Error popup is displayed");
			}

		} catch (Exception ex) {
			System.out.println("Error tc06: " + ex);
		}
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
		// System.out.println("waiting");
		// waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemsXpath));

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

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(99999);
		return number;
	}

	public void scrollToElement(By locatorXpath) {
		element = driver.findElement(locatorXpath);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitToElementClickable(By locator) {
		element = driver.findElement(locator);
		waitExplicit = new WebDriverWait(driver, 5);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
	}

}

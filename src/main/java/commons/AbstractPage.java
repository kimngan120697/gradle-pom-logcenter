package commons;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractPage {

	private WebDriverWait waitExplicit;
	private Actions action;
	private WebElement element;
	private By byXpath;
	private Select select;
	private JavascriptExecutor javaScript;
	/* ==============Selenium-Web-Browser=================== */

	// Selenium WebBrowser
	public void openUrl(WebDriver driver, String value) {
		driver.get(value);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	// Alert
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	// Windows
	public void switchToWindowByID(WebDriver driver) {

	}

	public void switchToWindowByTitle(WebDriver driver) {

	}

	public void closeAllWindowsWithoutParent(WebDriver driver) {

	}

	/* ==============Selenium-Web-Element=================== */
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpathLocator(locator));
	}

	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}

	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}

	public By byXpathLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, locator, values).click();
	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).sendKeys(value);
	}

	public void clearDataToElement(WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).clear();
	}

	public void clearDataToElement(WebDriver driver, String locator, String... value) {
		findElementByXpath(driver, locator, value).clear();
	}

	public void senkeyToElement(WebDriver driver, String locator, String valueToSenKey, String... values) {
		findElementByXpath(driver, locator, values).clear();
		findElementByXpath(driver, locator, values).sendKeys(valueToSenKey);
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public void getSelectedItemInDropdown(WebDriver driver) {

	}

	public void isDropdownMultiple(WebDriver driver) {

	}

	public void selectItemInCustomDropdown(WebDriver driver) {

	}

	public String getAttributeElement(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, locator, values).getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}

	public void checkTheCheckbox(WebDriver driver) {

	}

	public void uncheckTheCheckbox(WebDriver driver) {

	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isDisplayed();
		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator, values);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isDisplayed();
		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		Date date = new Date();
		System.out.println("Start time: " + date.toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			System.out.println("End time =" + date.toString());
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		Date date = new Date();
		System.out.println("Start time: " + date.toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator, values);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			System.out.println("End time =" + date.toString());
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isEnabled();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		try {
			return findElementByXpath(driver, locator, values).isEnabled();
		} catch (Exception ex) {
			return false;
		}
	}

	// Radio button, Checkbox
	// Danh cho Checkbox & radio button
	public boolean isElementSelected(WebDriver driver, String locator) {
		try {
			element = findElementByXpath(driver, locator);
			return element.isSelected();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isSelected();
		} catch (Exception ex) {
			return false;
		}
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.moveToElement(element).perform();
	}

	// Frame / Iframe

	// User Intersaction
	public void doubleClickToElement(WebDriver driver) {

	}

	public void rightClick(WebDriver driver) {

	}

	public void dragAndDrop(WebDriver driver) {

	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {

		element = findElementByXpath(driver, locator);
		element.sendKeys(key);
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... value) {

		element = findElementByXpath(driver, locator, value);
		element.sendKeys(key);
	}

	// Upload

	// Javascript Excutor
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		javaScript = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		javaScript.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		javaScript = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		javaScript.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	// Wait
	public void waitToElementPresence(WebDriver driver) {

	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}

	//
	public void waitToElementClickable(WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}

	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}

	// Override lai implicit timeout de findelement
	// Neu goi ham nay thi sau khi goi phai goi lai default
	// Vi du set short timeout thi sau do phai gan lai mac dinh la long timeout
	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void threadSleep(int miliSecond) throws InterruptedException {
		Thread.sleep(miliSecond);
	}

}

package br.com.dcm;

import java.awt.AWTException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.dcm.awt.RobotUtil;


public class Selenium {

	private WebDriver driver;

	public Selenium() {

	}

	public void startDriverFirefox() {
		this.startDriverFirefox(null);
	}

	public void startDriverFirefox(String youProfile) {
		this.startDriverFirefox(youProfile, false);
	}

	/**
	 * @param youProfile is required if has captcha in you web page by example
	 *                   "DEV_ROBO"
	 */
	public void startDriverFirefox(String youProfile, boolean browserOcult) {
		String extensao = "";
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			extensao = ".exe";
		}
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + File.separator + "driver"
				+ File.separator + "geckodriver" + extensao);

		if (youProfile != null && !youProfile.trim().isEmpty()) {
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile ffprofile = profile.getProfile(youProfile);
			ffprofile.setPreference("browser.download.folderList", 2);
			ffprofile.setPreference("browser.download.dir", "C:\\Users\\rpa\\Downloads");
			ffprofile.setPreference("browser.download.useDownloadDir", true);
			ffprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			ffprofile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setPreference("security.tls.version.min", 1);

			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", "C:\\Users\\rpa\\Downloads");
			options.addPreference("browser.download.useDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			options.addPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
			options.setProfile(ffprofile);

			if (browserOcult) {
				options.setHeadless(true);
			}

			driver = new FirefoxDriver(options);
		} else {
			driver = new FirefoxDriver();
		}
	}

	public void startDriverBrave(String path) {
		startDriverGoogle(null, false, false, null, path);
	}

	public void startDriverBrave() {
		String osName = System.getProperty("os.name").toLowerCase();
		String setBinary = new String();

		String bravePath = System.getenv("BRAVE_PATH");
		if (!(bravePath == null)) {
			startDriverGoogle(null, false, false, null, bravePath);
			return;
		}

		if (osName.contains("windows")) {
			setBinary = "C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
		} else if (osName.contains("mac")) {
//            /Users/lucaspiressimao/Applications
			setBinary = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
		}
		startDriverGoogle(null, false, false, null, setBinary);
	}

	public void startDriverGoogle() {
		startDriverGoogle(null, false, false, null, null);
	}

	public void startDriverGoogle(String existent) {
		startDriverGoogle(null, true, false, existent, null);
	}

	public void startDriverGoogle(String pathUserChrome, boolean useOptions, boolean browserOcult) {
		startDriverGoogle(pathUserChrome, useOptions, browserOcult, null, null);
	}

	public void startDriverGoogle(String pathUserChrome, boolean useOptions, boolean browserOcult, String existent) {
		startDriverGoogle(pathUserChrome, useOptions, browserOcult, existent, null);
	}

	public void startDriverGoogle(String pathUserChrome, boolean useOptions, boolean browserOcult, String existent,
			String setBinary) {
		String extensao = "";
		String pathChrome = "";
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			extensao = ".exe";
		}

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + File.separator + "driver"
				+ File.separator + "chromedriver" + extensao);

		pathChrome = System.getProperty("user.home") + File.separator + "driver" + File.separator + "profiles";

		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);

		if (useOptions) {
			

//			options.addExtensions(new File("C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Sync Extension Settings\\ccdpdjieoopkfanmkofiabeililfmcce"));

			if (pathUserChrome != null && !pathUserChrome.isEmpty()) {
				options.addArguments("--user-data-dir=" + pathChrome + File.separator + pathUserChrome);
			}
//			options.addArguments("--single-process");
//			options.addArguments("--load-extension=C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Sync Extension Settings");
//			options.addArguments("--start-maximized");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-gpu");
			options.addArguments("chromedriver --whitelisted-ips='*'");
			options.addArguments("disable-infobars");
			if (browserOcult) {
				options.addArguments("--headless");
			}
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//			driver = new ChromeDriver(capabilities.chrome());
			if (!(existent == null)) {
				options.setExperimentalOption("debuggerAddress", existent);
			}

			driver = new ChromeDriver(options);

			if (!(existent == null)) {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
			}

		} else if (!(setBinary == null)) {
			options.setBinary(setBinary);
			driver = new ChromeDriver(options);
		} else {
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();

	}

	public void setValueSelectById(String attribute, Object object) {
		if (attribute == null || object == null || object.toString() == null) {
			return;
		}

		// System.out.println("Select attribute: " + attribute + "\nobject: " +
		// object.toString());
		Select dropdown = new Select(driver.findElement(By.id(attribute)));
		dropdown.selectByValue(object.toString());
	}

	public void setValueSelectByName(String value, Object object) {
		if (value == null || object == null || object.toString() == null) {
			return;
		}

		// System.out.println("Select attribute: " + value + "\nobject: " +
		// object.toString());
		Select dropdown = new Select(driver.findElement(By.name(value)));
		dropdown.selectByValue(object.toString());
	}

	public WebElement getElementById(String id) {
		return driver.findElement(By.id(id));
	}

	public WebElement getElementByClassName(String id) {
		return driver.findElement(By.className(id));
	}

	public List<WebElement> getElementsByClassName(String id) {
		return driver.findElements(By.className(id));
	}

	public WebElement getElementByName(String name) {
		return driver.findElement(By.name(name));
	}

	public WebElement getElementByXPath(String xpathExpression) {
		return driver.findElement(By.xpath(xpathExpression));
	}

	public WebElement getElementByTagName(String tagName) {
		return driver.findElement(By.tagName(tagName));
	}

	public List<WebElement> getElementsByTagName(String tagName) {
		return driver.findElements(By.tagName(tagName));
	}

	public List<WebElement> getElementsByName(String name) {
		return driver.findElements(By.name(name));
	}

	public WebElement getElementByXpath(String tag, String attribute, String value) {
		if (tag == null || attribute == null) {
			return null;
		}

		WebElement element = driver.findElement(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
		// System.out.println("tag: " + tag + "\nattribute: " + attribute + "\nvalue: "
		// + value);
		return element;
	}

	public List<WebElement> getElementsByXpath(String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		return elements;
	}

	public List<WebElement> getElementsByXpath(String tag, String attribute, String value) {
		if (tag == null || attribute == null) {
			return null;
		}

		List<WebElement> elements = driver.findElements(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
		// System.out.println("tag: " + tag + "\nattribute: " + attribute + "\nvalue: "
		// + value);
		return elements;
	}

	public void openURL(String url) {
		driver.get(url);
	}

	/**
	 * @param attribute
	 * @param object
	 */
	public void setValueByName(String value, Object object) {
		setValueByName(value, object, false);
	}

	/**
	 * @param attribute
	 * @param object
	 * @param keyTab
	 */
	public void setValueByName(String value, Object object, boolean keyTab) {
		if (value == null || object == null || object.toString() == null) {
			return;
		}

		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value + "\nobject: " + object.toString());
		element.clear();
		element.sendKeys(object.toString());
		if (keyTab) {
			element.sendKeys(Keys.TAB);
		}

	}

	public void setValueByName(String value, Object object, Keys key) {
		if (value == null || object == null || object.toString() == null) {
			return;
		}

		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value + "\nobject: " + object.toString());
//		element.clear();
		element.sendKeys(object.toString());
		if (key != null) {
			element.sendKeys(key);
		}
	}

	public void keyTabByName(String value) {
		if (value == null) {
			return;
		}

		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value);
//		element.clear();
		element.sendKeys(Keys.TAB);

	}
	
	public void keyTabById(String value) {
		if (value == null) {
			return;
		}

		WebElement element = driver.findElement(By.id(value));
		element.sendKeys(Keys.TAB);
	}
	
	public void keyEnterById(String value) {
		if (value == null) {
			return;
		}

		WebElement element = driver.findElement(By.id(value));
		element.sendKeys(Keys.ENTER);

	}

	public void submitByName(String value) {
		if (value == null) {
			return;
		}

		WebElement searchBox = driver.findElement(By.name(value));
		searchBox.submit();
	}

	/**
	 * @param attribute
	 * @param object
	 */
	public void setValueById(String attribute, Object object) {
		if (attribute == null || object == null || object.toString() == null) {
			return;
		}

		WebElement element = driver.findElement(By.id(attribute));
		// System.out.println("attribute: " + attribute + "\nobject: " +
		// object.toString());
		element.clear();
		element.sendKeys(object.toString());
	}

	/**
	 * @param tag
	 * @param attribute
	 * @param value
	 * @param object
	 */
	public void setValueByXpath(String tag, String attribute, String value, Object object) {
		if (tag == null || attribute == null || object == null || object.toString() == null) {
			return;
		}

		WebElement element = driver.findElement(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
		// System.out.println("tag: " + tag + "\nattribute: " + attribute + "\nvalue: "
		// + value + "\nobject: " + object.toString());
		element.clear();
		element.sendKeys(object.toString());
	}

	/**
	 * @param tag
	 * @param attribute
	 * @param value
	 */
	public void setClickByXpath(String tag, String attribute, String value) {
		if (tag == null || attribute == null || value == null) {
			return;
		}

		WebElement element = driver.findElement(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
		// System.out.println("tag: " + tag + "\nattribute: " + attribute + "\nvalue: "
		// + value);
		element.click();
	}

	public void setClickByName(String value) {
		if (value == null || value.isEmpty()) {
			return;
		}

		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value);
		element.click();
	}

	public void setClickById(String attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return;
		}

		// System.out.println("attribute: " + attribute);
		WebElement element = driver.findElement(By.id(attribute));
		element.click();
	}

	public void setClickByClassName(String value) {
		if (value == null || value.isEmpty()) {
			return;
		}

		WebElement element = driver.findElement(By.className(value));
		// System.out.println("attribute: " + value);
		element.click();
	}

	public void setClickByCssSeletor(String seletor) {
		if (seletor == null || !seletor.isEmpty()) {
			return;
		}

		WebElement element = driver.findElement(By.cssSelector(seletor));
		// System.out.println("attribute: " + seletor);
		element.click();
	}

	public boolean elementIsEmpyty(String value) {
		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value);
		return (element.getText() != null ? element.getText().trim().isEmpty() : false);
	}

	public String getValueSubElementByIdElement(String id, String tag, String attribute, String value) {
		WebElement div = driver.findElement(By.id(id));

		List<WebElement> imgs = div.findElements(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < imgs.size(); i++) {
			sb.append(imgs.get(i).getAttribute("src").toString().replaceAll("http://wwwv.site/images/capchs/", "")
					.replaceAll(".png", ""));
		}
		return sb.toString();
	}

	/**
	 * @param seconds
	 * @see wait for seconds
	 */
	public void implicitlyWaitForSeconds(int seconds) {
		// set implicit wait
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * @param seconds
	 * @see wait for seconds
	 */
	public void implicitlyWaitForMilliSeconds(int milliseconds) {
		// set implicit wait
		driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * @param time
	 * @see pageLoadTimeout for minutes
	 */
	public void pageLoadTimeout(long time) {
		// set implicit wait
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.MINUTES);
	}

	public void executeScript(String script) {
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
			jsDriver.executeScript(script);
			implicitlyWaitForMilliSeconds(500);
		} else {
			throw new IllegalStateException("Esse driver não suporta javascript");
		}
	}

	public String getValueByScript(String script) {
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
			return (String) jsDriver.executeScript("return " + script);
			// implicitlyWaitForMilliSeconds(500);
		} else {
			throw new IllegalStateException("Esse driver não suporta javascript");
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String getTextAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			return "";
		}
	}

	public void alertAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void alertDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void setCaptchaById(String attribute) throws AWTException {
		setCaptchaById(attribute, 20000);
	}

	public void setCaptchaById(String attribute, int miliSeconds) throws AWTException {
		if (attribute == null) {
			return;
		}

		WebElement element = driver.findElement(By.id(attribute));
		// System.out.println("attribute: " + attribute);
		element.clear();
		element.sendKeys("");

		RobotUtil robotUtil = new RobotUtil();
		robotUtil.sleep(1000);
		robotUtil.ctrlShiftVk_3();
		robotUtil.sleep(500);
		robotUtil.ctrlShiftVk_6();
		// wait for 20 seconds
		robotUtil.sleep(miliSeconds);
	}

	public void setCaptchaByName(String value) throws AWTException {
		setCaptchaByName(value, 20000);
	}

	public void setCaptchaByName(String value, int miliSeconds) throws AWTException {
		if (value == null) {
			return;
		}

		WebElement element = driver.findElement(By.name(value));
		// System.out.println("attribute: " + value);
		element.clear();
		element.sendKeys("");

		RobotUtil robotUtil = new RobotUtil();
		robotUtil.sleep(1000);
		robotUtil.ctrlShiftVk_3();
		robotUtil.sleep(500);
		robotUtil.ctrlShiftVk_6();
		// wait for 20 seconds
		robotUtil.sleep(miliSeconds);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getHtml() {
		return driver.getPageSource();
	}

	public String getTextById(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element.getAttribute("value");
	}

	public void clickRightButtonMouseById(String id) {

		WebElement element = driver.findElement(By.id(id));

		Actions action = new Actions(driver);

		action.contextClick(element).build().perform();
	}

	public void doubleClickById(String id) {

		WebElement element = driver.findElement(By.id(id));

		Actions action = new Actions(driver);

		action.doubleClick(element).build().perform();
	}

	public By getByXPath(String tag, String attribute, String value) {
		return By.xpath("//" + tag + "[@" + attribute + "='" + value + "']");
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void exit() {
		if (driver != null) {
//			driver.close();
			driver.quit();
		}
	}

	public void setCaptchaByIdCommand(String attribute) {
		if (attribute == null) {
			return;
		}

		WebElement element = driver.findElement(By.id(attribute));
		// System.out.println("attribute: " + attribute);
		element.sendKeys("");

		Actions keyAction = new Actions(driver);
		keyAction.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("3").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT)
				.perform();
		keyAction.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("6").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT)
				.perform();
	}

	public void openNewTab(String url) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		driver.get(url);
	}

	public void openNewTab2(String url) {
		((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
	}

	public void closeTab() {
		((JavascriptExecutor) driver).executeScript("window.close();");
	}

	public void setTabWindow(int index) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void clickInRecaptchaByXPath(String tag, String attribute, String value) {
//		WebElement iFrame = driver.findElement(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
//		driver.switchTo().frame(iFrame);

		driver.switchTo().frame(0);

		WebElement iFrame_checkbox = driver.findElement(By.xpath("//" + tag + "[@" + attribute + "='" + value + "']"));
		iFrame_checkbox.click();
	}

	public void setClickByLinkText(String text) {
		WebElement link = driver.findElement(By.linkText(text));
		link.click();
	}

	public void waitForElement(int timeout, By selector) {
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(selector));
	}

	public void waitForNotPresentElement(int timeout, By selector) {
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.invisibilityOfElementLocated(selector));
	}

	public void goToPreviousPage() {
		driver.navigate().back();
	}

	public void selectByVisibleTextById(String attribute, String text) {
		WebElement element = driver.findElement(By.id(attribute));
		if (element != null) {
			new Select(element).selectByVisibleText(text);
		}
	}

	public void selectByPartVisibleTextById(String attribute, String text, Integer index) {
		WebElement element = driver.findElement(By.id(attribute));
		if (element != null) {

            element.findElements(By.xpath(".//option[contains(., " + Quotes.escape(text) + ")]")).get(index).click();
//			new Select(element).selectByVisibleText(text);
		}
	}

	public void selectByVisibleTextById2(String attribute, String text) throws Exception{
		WebElement element = driver.findElement(By.id(attribute));
		element.click();
		Thread.sleep(500);
		if (element != null) {
			new Select(element).selectByVisibleText(text);
		}
	}

	public void selectByVisibleTextByName(String attribute, String text) {
		WebElement element = driver.findElement(By.name(attribute));
		if (element != null) {
			new Select(element).selectByVisibleText(text);
		}
	}
}
package br.com.dcm;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OpenNewTab {

	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(
						System.getProperty("user.home") + File.separator + "driver" + File.separator + "chromedriver"))
				.usingAnyFreePort().build();
		try {
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void createAndStopService() {
		service.stop();
	}

	@Before
	public void createDriver() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("user-data-dir=" + System.getProperty("user.home") + "/.config/google-chrome");
		options.addArguments("--single-process");
		options.addArguments("--start-maximized");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new RemoteWebDriver(service.getUrl(), capabilities.chrome());
	}

	@After
	public void quitDriver() {
		driver.quit();
	}

	@Test
	public void testGoogleSearch() {
		try {

			driver.manage().window().maximize();

			String baseUrl = "http://www.google.co.uk";
			driver.get(baseUrl);

			((JavascriptExecutor) driver).executeScript("window.open()");

			Thread.sleep(5000);

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1)); // switches to new tab
			driver.get("https://www.facebook.com");

			driver.switchTo().window(tabs.get(0)); // switch back to main screen
			driver.get("https://www.uol.com.br");

			Thread.sleep(15000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(driver.getTitle());
		assertEquals("webdriver - Pesquisa Google", driver.getTitle());
	}

}

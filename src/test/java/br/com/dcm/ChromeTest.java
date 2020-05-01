package br.com.dcm;


import java.io.File;
import java.io.IOException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class ChromeTest extends TestCase {

	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(System.getProperty("user.home") + File.separator + "driver" + File.separator + "chromedriver.exe"))
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

		options.addArguments("--user-data-dir="+"C:\\Program Files (x86)\\Google\\Chrome\\Application");
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
		driver.get("http://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Magazine Luiza");
		element.submit();
		
		System.out.println(driver.getTitle());
		assertEquals("Palmeiras não tem mundial - Pesquisa Google", driver.getTitle());
	}
}
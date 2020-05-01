package br.com.dcm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ChomeDriver {

	@Test
	public void preencheFormularioCorreiosBuscaLogradouroPorBairro() {
		String extensao = "";
//        Selenium selenium = new Selenium();
//
//        selenium.startDriverBrave();

        System.out.println(System.getenv("SPRING_PROFILES_ACTIVE"));




//		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//			extensao = ".exe";
//		}
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + File.separator + "driver"
//				+ File.separator + "chromedriver" + extensao);
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--disable-gpu");
//		options.addArguments("--headless");
//
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		WebDriver driver = new ChromeDriver(capabilities.chrome());
//		// Visita a página do Correios
//		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/buscaLogBairro.cfm");
//
//		// Escolhe o valor de UF
//		Select selectUF = new Select(driver.findElement(By.name("UF")));
//		selectUF.selectByVisibleText("RJ");
//		// Preenche a Localidade com o valor "Rio de Janeiro"
//		WebElement inputLocalidade = driver.findElement(By.name("Localidade"));
//		inputLocalidade.sendKeys("Rio de Janeiro");
//		// Preenche o campo Bairro com o valor "Copacabana"
//		WebElement inputBairro = driver.findElement(By.name("Bairro"));
//		inputBairro.sendKeys("Copacabana");
//
//		// clica no botão Buscar
//		WebElement buttonBuscar = driver.findElement(By.cssSelector("input[type='submit'"));
//		buttonBuscar.click();
//
//		// verifica se há resultados
//		String codigoPagina = driver.getPageSource();
//		assertTrue(codigoPagina.contains("DADOS ENCONTRADOS COM SUCESSO."));
//		assertNotNull(driver.findElement(By.cssSelector("table.tmptabela")));
//
//		driver.quit();

	}

}

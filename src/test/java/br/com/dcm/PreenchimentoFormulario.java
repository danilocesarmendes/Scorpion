package br.com.dcm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.dcm.Selenium;

public class PreenchimentoFormulario {

//	@Test
//	public void preencheFormularioCorreiosBuscaLogradouroPorBairro() {
//		
//		String extensao = "";
//		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//			extensao = ".exe";
//		}
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + File.separator + "driver"
//				+ File.separator + "chromedriver" + extensao);
//		
//		ChromeOptions options = new ChromeOptions();
////		options.addArguments("--headless");
////		options.addArguments("--single-process");
//		options.addArguments("--start-maximized");
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--disable-gpu");
//		options.addArguments("chromedriver --whitelisted-ips='*'");
//		options.addArguments("disable-infobars");
//
//		WebDriver driver = new ChromeDriver(options);
//		
////		WebDriver driver = new ChromeDriver();
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
//		assertThat(codigoPagina, Matchers.containsString("DADOS ENCONTRADOS COM SUCESSO."));
//		assertNotNull(driver.findElement(By.cssSelector("table.tmptabela")));
//		
//		System.out.println("Terminou");
//
//		driver.quit();
//	}

	@Test
	public void preencheFormularioCorreiosBuscaLogradouroPorBairroModoHeadless() {
		Selenium selenium = new Selenium();
		
		selenium.startDriverGoogle("", true, true);
		

		// Visita a página do Correios
		selenium.openURL("http://www.buscacep.correios.com.br/sistemas/buscacep/buscaLogBairro.cfm");

		// Escolhe o valor de UF
		Select selectUF = new Select(selenium.getDriver().findElement(By.name("UF")));
		selectUF.selectByVisibleText("RJ");
		// Preenche a Localidade com o valor "Rio de Janeiro"
		WebElement inputLocalidade = selenium.getDriver().findElement(By.name("Localidade"));
		inputLocalidade.sendKeys("Rio de Janeiro");
		// Preenche o campo Bairro com o valor "Copacabana"
		WebElement inputBairro = selenium.getDriver().findElement(By.name("Bairro"));
		inputBairro.sendKeys("Copacabana");

		// clica no botão Buscar
		WebElement buttonBuscar = selenium.getDriver().findElement(By.cssSelector("input[type='submit'"));
		buttonBuscar.click();

		// verifica se há resultados
		String codigoPagina = selenium.getHtml();
		assertThat(codigoPagina, Matchers.containsString("DADOS ENCONTRADOS COM SUCESSO."));
		assertNotNull(selenium.getDriver().findElement(By.cssSelector("table.tmptabela")));

		System.out.println("Terminou");
		selenium.exit();
		
	}

}

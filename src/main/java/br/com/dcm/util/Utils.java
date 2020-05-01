package br.com.dcm.util;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.text.MaskFormatter;

import org.jsoup.Jsoup;

public class Utils {

	public static String html2text(String html) {
		return Jsoup.parse(html).text();
	}

	public static String extractValue(String frase, String palavraInicial, String palavraFinal) {
		return extractValue(frase, palavraInicial, palavraFinal, true);
	}

	public static String extractValue(String frase, String palavraInicial, String palavraFinal, boolean multiline) {
		String valor = extractTextRightOf(frase, palavraInicial, multiline);

		valor = extractTextLefttOf(valor, palavraFinal, multiline);

		return valor;
 
	}

	public static String extractTextRightOf(String frase, String palavra, boolean multiline) {
		String valor = "";

		String splitFrase[] = frase.split(palavra);

		frase = palavra + splitFrase[splitFrase.length - 1];

		Pattern p = (multiline ? Pattern.compile(palavra + ".*", Pattern.DOTALL) : Pattern.compile(palavra + ".*"));
		Matcher m = p.matcher(frase);
		if (m.find()) {
			frase = m.group().replaceAll(palavra, "");
			valor = frase;
		}

		return valor.trim().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").trim();
	}

	public static String extractTextLefttOf(String frase, String palavra, boolean multiline) {
		String valor = "";

		String splitFrase[] = frase.split(palavra);

		frase = splitFrase[0] + palavra;

		Pattern p = (multiline ? Pattern.compile(".*" + palavra, Pattern.DOTALL) : Pattern.compile(".*" + palavra));
		Matcher m = p.matcher(frase);
		if (m.find()) {
			frase = m.group().replaceAll(palavra, "");
			valor = frase;
		}

		return valor.trim().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").trim();
	}

	public static String preparaFrase(String frase) {
		return frase.replaceAll("\\(", "#").replaceAll("\\)", "#")
				.replaceAll("\n", " ").replaceAll("\t", " ").replaceAll("\r", " ");
	}

	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static boolean isCpf(String cpf) {

		cpf = cpf.replaceAll("\\.", "").replaceAll("\\-", "");
		// considera-se erro cpf's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do cpf em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String formatCpf(String cpf) throws ParseException {
		MaskFormatter mask = new MaskFormatter("###.###.###-##");
		mask.setValueContainsLiteralCharacters(false);
		System.out.println("cpf : " + mask.valueToString(cpf));

		return mask.valueToString(cpf);
	}
	
	public static String formatCnpj(String cnpj) throws ParseException {
		MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
		mask.setValueContainsLiteralCharacters(false);
//		System.out.println("CNPJ : " + mask.valueToString(cnpj));

		return mask.valueToString(cnpj.replaceAll("\\D", ""));
	}
	
	public static String formatCelular(String fone) {
		try {
			if(fone == null) {
				return fone;
			}
			String mask_ = "";
			fone = fone.replaceAll("\\D", "");
			if(fone.length() == 10) {
				mask_ = "(##)####-####";
			} else if(fone.length() == 11) {
				mask_ = "(##)#####-####";
			} else if(fone.length() == 12) {
				mask_ = "+## (##)####-####";
			} else if(fone.length() == 13) {
				mask_ = "+## (##)#####-####";
			} else {
				return fone;
			}
			MaskFormatter mask = new MaskFormatter(mask_);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(fone.replaceAll("\\D", ""));
		}catch(ParseException e) {
			return fone;
		}
	}
	
	public static void printScreen(String folder, String nameImage, int positionX, int positionY, int sizeX, int sizeY) {
		printScreen(folder, nameImage, positionX, positionY, sizeX, sizeY, false);
	}
	
	public static void printScreen(String folder, String nameImage, int positionX, int positionY, int sizeX, int sizeY, boolean convertBlackWhite) {
		System.setProperty("java.awt.headless", "false");
		Robot robot;
		System.out.println("positionX" + positionX);
		System.out.println("positionY" +positionY);
		System.out.println("sizeX" +sizeX);
		System.out.println("sizeY" +sizeY);
		try {
			robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new // Captura a tela na àrea definida pelo retângulo
			Rectangle(positionX, positionY, sizeX, sizeY)); // aqui vc configura as posições xy e o tam da área
																		// que quer capturar

			/*
			 * OS PARAMETROS SÃO positionX INICIO DO RECORTE positionY INICIO DO RECORTE
			 * sizeX TAMANHO DO RECORTE sizeY TAMANHO DO RECORTE
			 */
//			bi = bi.getSubimage(positionX, positionY, sizeX, sizeY);

			// cria pasta caso não exista
			File file = new File(folder);
			if (!file.exists()) {
				file.mkdirs();
			}

			file = new File(folder + nameImage);
			
			//converte em preto e branco
			if(convertBlackWhite) {
				bi = image2BlackWhite(bi);
			}

			// salva a imagem
			ImageIO.write(bi, "jpg", file);// Salva a imagem

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public static BufferedImage image2GrayScale(BufferedImage image) {
		BufferedImage imageGray = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_BYTE_GRAY);  
		Graphics g = imageGray.getGraphics();  
		g.drawImage(image, 0, 0, null);  
		g.dispose();
		return imageGray;
	}
	
	public static BufferedImage image2BlackWhite(BufferedImage image1) {
		int w = image1.getWidth();
		int h = image1.getHeight();
		byte[] comp = { 0, -1 };
		IndexColorModel cm = new IndexColorModel(2, 2, comp, comp, comp);
		BufferedImage image2 = new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_INDEXED, cm);
		Graphics2D g = image2.createGraphics();
		g.drawRenderedImage(image1, null);
		g.dispose();
		return image2;
	}
	
	public static boolean isCelular(String celular) {
		if(celular == null) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("0000000000") || celular.replaceAll("\\D", "").equals("00000000000")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("1111111111") || celular.replaceAll("\\D", "").equals("11111111111")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("2222222222") || celular.replaceAll("\\D", "").equals("22222222222")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("3333333333") || celular.replaceAll("\\D", "").equals("33333333333")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("4444444444") || celular.replaceAll("\\D", "").equals("44444444444")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("5555555555") || celular.replaceAll("\\D", "").equals("55555555555")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("6666666666") || celular.replaceAll("\\D", "").equals("66666666666")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("7777777777") || celular.replaceAll("\\D", "").equals("77777777777")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("8888888888") || celular.replaceAll("\\D", "").equals("88888888888")) {
			return false;
		}
		if(celular.replaceAll("\\D", "").equals("9999999999") || celular.replaceAll("\\D", "").equals("99999999999")) {
			return false;
		}
		if(celular.contains("-")) {
		return celular.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
				 celular.matches(".((10)|([1-9][1-9]).)9?[6-9][0-9]{3}-[0-9]{4}") || 
				 celular.matches(".((10)|([1-9][1-9]).)9?[6-9][0-9]{3}-[0-9]{5}");
		}else {
			return celular.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}[0-9]{4}") ||
					celular.matches(".((10)|([1-9][1-9]).)9?[6-9][0-9]{3}[0-9]{4}");
		}
    }
	
	public static boolean isTelefone(String telefone) {
		if(telefone == null) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("0000000000") || telefone.replaceAll("\\D", "").equals("00000000000")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("1111111111") || telefone.replaceAll("\\D", "").equals("11111111111")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("2222222222") || telefone.replaceAll("\\D", "").equals("22222222222")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("3333333333") || telefone.replaceAll("\\D", "").equals("33333333333")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("4444444444") || telefone.replaceAll("\\D", "").equals("44444444444")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("5555555555") || telefone.replaceAll("\\D", "").equals("55555555555")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("6666666666") || telefone.replaceAll("\\D", "").equals("66666666666")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("7777777777") || telefone.replaceAll("\\D", "").equals("77777777777")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("8888888888") || telefone.replaceAll("\\D", "").equals("88888888888")) {
			return false;
		}
		if(telefone.replaceAll("\\D", "").equals("9999999999") || telefone.replaceAll("\\D", "").equals("99999999999")) {
			return false;
		}
		return telefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }
	
}

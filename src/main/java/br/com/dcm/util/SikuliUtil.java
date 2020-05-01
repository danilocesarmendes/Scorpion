package br.com.dcm.util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class SikuliUtil {

	public static void doubleClickInImage(String caminhoImage) {
		doubleClickInImage(caminhoImage, 30);
	}

	public static void doubleClickInImage(String caminhoImage, int seconds) {
		Screen s = new Screen();
		Match match = null;
		try {
			match = s.wait(caminhoImage, seconds);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		if (match != null) {
			int encontrouImagem = match.click();
			if(encontrouImagem == 0) {
				System.out.println("Imagem " + caminhoImage + " não encontrada");
			}else {
				System.out.println(caminhoImage + "\n" + match.toString());
			}
		} else {
			System.out.println("Imagem " + caminhoImage + " não encontrada"); 
		}
	}

	public static void clickLeftInImage(String caminhoImage) {
		clickLeftInImage(caminhoImage, 30);
	}

	public static void clickLeftInImage(String caminhoImage, int seconds) {
		Screen s = new Screen();
		Match match = null;
		try {
			match = s.wait(caminhoImage, seconds);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		if (match != null) {
			int encontrouImagem = match.click();
			if(encontrouImagem == 0) {
				System.out.println("Imagem " + caminhoImage + " não encontrada");
			}else {
				System.out.println(caminhoImage + "\n" + match.toString());
			}
		} else {
			System.out.println("Imagem " + caminhoImage + " não encontrada");
		}
	}

	public static void clickLeftInSequenceOfImage(String[] images, int seconds) {
		Screen s = new Screen();
		Match match = null;
		boolean encontrou = false;
		for (String imagem : images) {
			try {
				match = s.wait(imagem, seconds);
			} catch (FindFailed e) {
				e.printStackTrace();
			}

			if (match != null) {
				System.out.println(imagem + "\n" + match.toString());
				match.click();
				encontrou = true;
			}
		}
		if (!encontrou) {
			System.out.println("Imagem " + images[0] + " não encontrada");
		}
	}

	public static void clickLeftOnFirstImage(String[] images, int secondsLimt) {
		Screen s = new Screen();
		Match match = null;
		boolean encontrou = false;

		int valor = (secondsLimt / images.length) / 5;
		for (int i = 0; i < valor - 1; i++) {
			for (String imagem : images) {
				try {
					match = s.wait(imagem, 5);
				} catch (FindFailed e) {
					e.printStackTrace();
				}

				if (match != null) {
					System.out.println(imagem + "\n" + match.toString());
					match.click();
					encontrou = true;
				}
				if (encontrou) {
					System.out.println("Imagem " + images + " encontrada!");
					break;
				}
			}
		}
	}

	public static void clickRightInImage(String caminhoImage) {
		clickLeftInImage(caminhoImage, 30);
	}

	public static void clickRightInImage(String caminhoImage, int seconds) {
		Screen s = new Screen();
		Match match = null;
		try {
			match = s.wait(caminhoImage, seconds);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		if (match != null) {
			match.rightClick();
			int encontrouImagem = match.rightClick();
			if(encontrouImagem == 0) {
				System.out.println("Imagem " + caminhoImage + " não encontrada");
			}else {
				System.out.println(caminhoImage + "\n" + match.toString());
			}
		} else {
			System.out.println("Imagem " + caminhoImage + " não encontrada");
		}
	}

	public static boolean waitProcessComplete(Integer minutes, String caminhoImage) {
		boolean notFound = false;
		Screen s = new Screen();
		Match match = null;
		try {
			match = s.wait(caminhoImage, minutes != null ? minutes * 60 : 30 * 60);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		if (match != null) {
			System.out.println(match.toString());
			notFound = true;
			System.out.println("Processo Finalizado");
		}
		return notFound;
	}

	public static Match getRegion(String caminhoImage, int seconds) {
		Screen s = new Screen();
		Match match = null;
		try {
			match = s.wait(caminhoImage, seconds);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		if (match != null) {
			System.out.println(match.toString());
			System.out.println(match.getW()); // largura
			System.out.println(match.getH()); // altura
			System.out.println(match.getX()); // posição x
			System.out.println(match.getY()); // posição y
			return match;
		} else {
			System.out.println("Imagem " + caminhoImage + " não encontrada");
		}
		return null;
	}

	public static boolean showImageScreen(String caminhoImage, int seconds) {
		Screen s = new Screen();
		
		Match match = null;
		boolean showImage = false;
		try {
			match = s.wait(caminhoImage, seconds);

			if (match != null) {
//				match.hover(caminhoImage);
				System.out.println(caminhoImage + "\n" + match.toString());
				showImage = true;
			} else {
				System.out.println("Imagem " + caminhoImage + " não encontrada");
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return showImage;
	}

}

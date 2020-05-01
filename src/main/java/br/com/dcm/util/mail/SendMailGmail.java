package br.com.dcm.util.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailGmail {

	public static String send(EmailStruct email) {
		String response = "";

		try {
			// config. do email
			Properties mailProps = new Properties();
			mailProps.put("mail.transport.protocol", "smtp");
			mailProps.put("mail.smtp.starttls.enable", "true");
			mailProps.put("mail.smtp.host", "smtp.gmail.com");
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.smtp.user", "brenotropi@gmail.com");
			mailProps.put("mail.debug", "true");
			mailProps.put("mail.smtp.port", "465");
			mailProps.put("mail.smtp.socketFactory.port", "465");
			mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			mailProps.put("mail.smtp.socketFactory.fallback", "false");

			// eh necessario autenticar
			Session mailSession = Session.getInstance(mailProps, new Authenticator() {

				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("brenotropi@gmail.com", "0255394306bb");
				}
			});
			mailSession.setDebug(false);

			// config. da mensagem
			Message mailMessage = new MimeMessage(mailSession);

			// remetente
			mailMessage.setFrom(new InternetAddress("brenotropi@gmail.com", "Breno"));

			// destinatario
			mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getSendTo()));
			// email em copia
			if (email.getSendCC() != null && !email.getSendCC().isEmpty()) {
				for(String mailCC : email.getSendCC()) {
					mailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mailCC));
            	}
			}

			// mensagem que vai no corpo do email
			MimeBodyPart mbpMensagem = new MimeBodyPart();
			mbpMensagem.setContent(email.getMessage(), "text/html; charset=UTF-8");
			// mbpMensagem.setText(email.getCorpo());

//			partes do email
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbpMensagem);

//			String Endereco_Anexo = "";
//            if (!email.getAnexos().equalsIgnoreCase("")) { // se tiver alguma coisa anexada ela inicializa o comando abaixo
//                
//                    Endereco_Anexo = email.getAnexos();
//                    String imagem = Endereco_Anexo;
//                    File Arquivo = new File(imagem);
//                    //setando o anexo
//                    MimeBodyPart mbpAnexo = new MimeBodyPart();
//                    mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
//                    mbpAnexo.setFileName(Arquivo.getName());
//                    mp.addBodyPart(mbpAnexo);
//                
//            }
//			if (!(email.getAnexos() == null)) {
//				for (String a : email.getAnexos()) {
//					Endereco_Anexo = a;
//					String imagem = Endereco_Anexo;
//					File Arquivo = new File(imagem);
//					// setando o anexo
//					MimeBodyPart mbpAnexo = new MimeBodyPart();
//					mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
//					mbpAnexo.setFileName(Arquivo.getName());
//					mp.addBodyPart(mbpAnexo);
//				}
//			}

			// assunto do email
			mailMessage.setSubject(email.getSubject());

			// seleciona o conteudo
			mailMessage.setContent(mp);

			// envia o email
			Transport.send(mailMessage);
			response = "Email Enviado com Sucesso";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
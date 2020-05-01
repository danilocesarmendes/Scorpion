package br.com.dcm.util.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SimpleMail {

	public static void main(String[] args) {
		final String fromEmail = "rpa@bild.com.br"; // requires valid gmail id
		final String password = "#bivi@rpa"; // correct password for gmail id
		final String toEmail = "danilocesarmendes@gmail.com"; // can be any email id

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.office365.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "587"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.starttls.enable", "true"); // SMTP Port
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.connectiontimeout", "20000");
		props.put("mail.smtp.user", "rpa@bild.com.br");
		props.put("mail.smtp.password", "#bivi@rpa");
		
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		EmailUtil.sendEmail(session, toEmail, "SSLEmail Testing Subject", "SSLEmail Testing Body");

//		EmailUtil.sendAttachmentEmail(session, toEmail, "SSLEmail Testing Subject with Attachment",
//				"SSLEmail Testing Body with Attachment");
//
//		EmailUtil.sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image",
//				"SSLEmail Testing Body with Image");

	}

}
package br.com.dcm.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailOffice365 {
    
	private static final String SERVIDOR_SMTP = "smtp.office365.com";
    private static final int PORTA_SERVIDOR_SMTP = 587;



    public static void send(final EmailStruct ems) {
    	final Session session = Session.getInstance(getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ems.getSmtpUser(), ems.getSmtpPassword());
            }

        });
        try {
            final Message message = new MimeMessage(session);
            
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(ems.getSendTo()));
            if(ems.getSendCC()!=null && !ems.getSendCC().isEmpty()) {
            	for(String mailCC : ems.getSendCC()) {
            		if(mailCC != null && !mailCC.isEmpty()) {
            			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mailCC));
            		}
            	}
            }
            message.setFrom(new InternetAddress(ems.getFrom()));
            message.setSubject(ems.getSubject());
            message.setText(ems.getMessage());
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
             ex.printStackTrace();
        }
    }

    private static Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    }
    
}
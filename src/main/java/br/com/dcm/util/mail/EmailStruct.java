package br.com.dcm.util.mail;

import java.util.ArrayList;
import java.util.List;

public class EmailStruct {

	private String smtpServer;
	private String smtpPort;
	private String smtpUser;
	private String smtpPassword;
	private String subject;
	private String message;
	private String sendTo;
	private List<String> sendCC;
	private String from;
	private String name;
	private boolean useSSL;
	private int messageType;
	private String inlineImage;
	private List<String> inlineImages;

	private List<String> filenames;

	public EmailStruct() {
		sendCC = new ArrayList<String>();
		inlineImages = new ArrayList<String>();
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public List<String> getSendCC() {
		return sendCC;
	}

	public void setSendCC(List<String> sendCC) {
		this.sendCC = sendCC;
	}

	public boolean isUseSSL() {
		return useSSL;
	}

	public void setUseSSL(boolean useSSL) {
		this.useSSL = useSSL;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public void addAttachedFilename(String filename) {
		if (filenames == null) {
			filenames = new ArrayList<String>();
		}
		filenames.add(filename);
	}

	public List<String> getAttachedFilenames() {
		return this.filenames;
	}

	public String getInlineImage() {
		return inlineImage;
	}

	public void setInlineImage(String inlineImage) {
		this.inlineImage = inlineImage;
	}

	public List<String> getInlineImages() {
		return inlineImages;
	}

	public void setInlineImages(List<String> inlineImages) {
		this.inlineImages = inlineImages;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMails(String emails) {
		String[] _listaEmails = new String[100];
		boolean emailUnico = true;
		if (emails.contains(",")) {
			emailUnico = false;
			_listaEmails = emails.split(",");
		}
		if (emails.contains(";")) {
			emailUnico = false;
			_listaEmails = emails.split(";");
		}
		if (emails.contains("|")) {
			emailUnico = false;
			_listaEmails = emails.split("|");
		}
		if (!emailUnico) {
			for (String mail : _listaEmails) {
				if(mail != null && !mail.trim().isEmpty()) {
					sendCC.add(mail);
				}
			}
		} else {
			sendCC.add(emails);
		}
	}

}

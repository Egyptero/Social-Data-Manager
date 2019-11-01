package cxengage.tools.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cxengage.facebook.conf.pojos.Tenant;

public class SendEmail {
	private static SendEmail sendEmail;
	
	private SendEmail() {
	}
	
	public static SendEmail getInstance() {
		if(sendEmail == null)
			sendEmail = new SendEmail();
		return sendEmail;
	}
	
	public void sendErrorMail(String subjectLine ,StackTraceElement[] traceElements,String errorMessage, Tenant tenant) {
		// Sender's email ID needs to be mentioned
		String from = "maref@istnetworks.com";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.user", "maref@istnetworks.com");
		properties.setProperty("mail.password", "omar2007");
		
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getInstance(properties,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("maref@istnetworks.com", "omar2007");
			}
		  });
		
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress("instGenesys@istnetworks.com"));
			new InternetAddress();
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tenant.getNotificationList()));
			// Set Subject: header field
			message.setSubject(subjectLine);
			String stackTraceMessage = "";
			for(StackTraceElement traceElement : traceElements) {
				stackTraceMessage += traceElement.toString()+"\r\n";
			}
			// Now set the actual message
			String contentMsg = "Dear "+tenant.getUser()+":\r\n"+"The Engine was facing difficulties in retreiving the page "+tenant.getPage()+".\r\n"
					+ "Please check the tenant status at the link http://emp.istnetworks.com:8080/InstGenesys/InstGenesysAdmin.html\r\n"
					+ "You can check the details of the message as the following:\r\n"
					+ "=================================\r\n"
					+ errorMessage + "\r\n"
					+ "=================================\r\n"
					+ "Stack Trace Below for more details\r\n"
					+ stackTraceMessage
					+ "Thanks,\r\n"
					+ "Auto generated message from the InstGenesys";
					
			message.setText(contentMsg);
			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}

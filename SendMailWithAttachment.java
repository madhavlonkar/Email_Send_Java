package utility;


import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailWithAttachment {

	// Run the mail example
	public static void main(String[] args) {
		// Send email
		sendEmail();
		
	}
	
	/**
	 * Send the email via SMTP using TLS and SSL
	 */
	private static void sendEmail() {
 
		// Create all the needed properties
		Properties connectionProperties = new Properties();
		// SMTP host
		connectionProperties.put("mail.smtp.host", "host");
		// Is authentication enabled
		connectionProperties.put("mail.smtp.auth", "true");
		// Is TLS enabled
		//connectionProperties.put("mail.smtp.starttls.enable", "true");
		// SSL Port
		connectionProperties.put("mail.smtp.socketFactory.port", "port");
		// SSL Socket Factory class
		connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// SMTP port, the same as SSL port :)
		connectionProperties.put("mail.smtp.port", "port");
		
		System.out.print("Creating the session...");
		
		// Create the session
		Session session = Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() {	// Define the authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("Username","Password");
					}
				});
		
		System.out.println("done!");
		
		// Create and send the message
		try {
//			// Create the message 
//			Message message = new MimeMessage(session);
//			// Set sender
//			message.setFrom(new InternetAddress("From-Email"));
//			// Set the recipients
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("To-Email"));
//			// Set message subject
//			message.setSubject("Please Check My Mail");
//			// Set message text
//			message.setText("You Otp Is 12345 .This Will Expire In 5 Min");
			
			
			//Sending Attachment
			Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress("From-Email"));
			   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("madhavlonkar2@gmail.com"));
			   message.setSubject("My Name Is Madhav An Cyber Security Expert");
//			   message.setText("You Otp Is 12345 .This Will Expire In 5 Min");
			   
			   
			   String msg = "You Otp Is 12345 .This Will Expire In 5 Min";
			    
			   MimeBodyPart mimeBodyPart = new MimeBodyPart();
			   mimeBodyPart.setContent(msg, "text/html");
			     
			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(mimeBodyPart);
			    
			   MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			   attachmentBodyPart.attachFile(new File("C:\\Users\\Root\\Pictures\\Screenshots\\Screenshot (3).png"));
			   multipart.addBodyPart(attachmentBodyPart);
			   message.setContent(multipart);
			 
			   Transport.send(message);	   
			   
			System.out.print("Sending message...");
			// Send the message
			Transport.send(message);
			
			System.out.println("done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
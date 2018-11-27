package service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Service to send mails
 * @author small44
 *
 */
public class MailService {
	
	public static final String PROPERTIES_FILE ="/home/small44/000/email.properties";

	/**
	 * Send the email
	 * @param subject the subject of the email
	 * @param message the message of the email
	 * @param from email sender
	 * @param to email receiver
	 */
	public static void sendMail(String subject,String message,String from,String to) {
		try {
			//PropertiesFileIO.saveMailProperties(PROPERTIES_FILE, "ismailghedamsi2", "thephenomenon");
			
			Properties props = PropertiesFileIO.getMailProperties(PROPERTIES_FILE);
			Authenticator auth = new SMTPAuthenticator(props.getProperty("mail.user"),props.getProperty("mail.password"));
			Session session = Session.getInstance(props, auth);
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			
			Transport.send(mimeMessage);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Authenticator for email sender
	 * @author small44
	 *
	 */
	private static class SMTPAuthenticator extends Authenticator {

		private PasswordAuthentication authentication;

		public SMTPAuthenticator(String login, String password) {
			authentication = new PasswordAuthentication(login, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
	
	
}

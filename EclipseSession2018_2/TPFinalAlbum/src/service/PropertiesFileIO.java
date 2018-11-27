package service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Create and read the properties file
 * @author small44
 *
 */
public class PropertiesFileIO {
	
	/**
	 * Read the properties file
	 * @param fileName the location of the mail properties file
	 * @return the properties file
	 */
	public static Properties getMailProperties(String fileName) {
		
		Properties props = new Properties();
		
		try {
			 props.load(new FileInputStream(fileName));
			 
			 props.setProperty("mail.user", props.getProperty("login"));
			 props.setProperty("mail.password", props.getProperty("password"));
			 
			 props.setProperty("mail.host", props.getProperty("mail.host"));
			 props.setProperty("mail.smtp.port", props.getProperty("mail.smtp.port"));
			 props.setProperty("mail.smtp.auth", props.getProperty("mail.smtp.auth"));
			 props.setProperty("mail.smtp.starttls.enable", props.getProperty("mail.smtp.starttls.enable"));
			 
			 props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			 props.setProperty("mail.smtp.socketFactory.port", "465");
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return props;
	}
	
	/**
	 * Save the mandatory properties file to send emails
	 * @param fileName the path and the name of the properties file
	 * @param login email login
	 * @param password email password
	 * @return the result of the operation
	 * @throws FileNotFoundException exception
	 * @throws IOException exception
	 */
	public static boolean saveMailProperties(String fileName,String login,String password) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("mail.user", login);
		prop.setProperty("mail.password", password);
		prop.setProperty("mail.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.store(new FileOutputStream(new File(fileName)), null);
		return false;
		
	}
	
	
}








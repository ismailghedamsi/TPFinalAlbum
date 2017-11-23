package cal.tpCertificatif.bean;

/**
 * Utilisateur normal 
 * @author small44
 *
 */
public class NormalUser extends AbstractUser {
	private int id;
	private static int counter;
	public NormalUser(String firstName, String lastName, int age) {
		super(firstName, lastName, age);
		id = counter;
		counter++;
		
	}
	public int getId() {
		return id;
	}
	
	

}

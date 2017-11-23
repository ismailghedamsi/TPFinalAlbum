package cal.tpCertificatif.bean;
/**
 * Utilisateur admin
 * @author small44
 *
 */
public class Admin extends AbstractUser {
	private int id;
	private static int counter;
	public Admin(String firstName, String lastName, int age) {
		super(firstName, lastName, age);
		id = counter;
		counter++;
	}
	
	public int getId() {
		return id;
	}

}

package cal.tpCertificatif.bean;

public class Admin extends AbstractUser {
	private int id;
	private static int counter;
	public Admin(String firstName, String lastName, String age) {
		super(firstName, lastName, age);
		id = counter;
		counter++;
	}
	
	public int getId() {
		return id;
	}

}

package cal.tpCertificatif.bean;

public class NormalUser extends AbstractUser {
	private int id;
	private static int counter;
	public NormalUser(String firstName, String lastName, String age) {
		super(firstName, lastName, age);
		id = counter;
		counter++;
		
	}
	public int getId() {
		return id;
	}
	
	

}

package bean;


import lombok.Getter;


/**
 * Admin user
 * @author small44
 *
 */
@Getter

public class Admin extends AbstractUser {
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private int id;
	
	/**
	 * Create an admin user
	 * @param firstName admin's first name
	 * @param lastName admin's last name
	 * @param age admin's age
	 */
	public Admin(String firstName, String lastName, int age) {
		super(firstName, lastName, age);
		
	}
	
	/**
	 * Create an admin user
	 * @param firstName admin's first name
	 * @param lastName admin's last name
	 * @param age admin's age
	 */
	public Admin(String firstName, String lastName, int age,Credentials credentials) {
		super(firstName, lastName, age,credentials);
		
	}

}

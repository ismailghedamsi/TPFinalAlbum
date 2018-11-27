package bean;

import lombok.Getter;

/**
 * Utilisateur normal 
 * @author small44
 *
 */
@Getter
public class NormalUser extends AbstractUser {
	private int id;

  /**
   * Normal user class
   * @param firstName normal user firstName
   * @param lastName normal user lastName
   * @param age normal user age;
   * @param credentials login and password
   */
	public NormalUser(String firstName, String lastName, int age,Credentials credentials) {
		super(firstName, lastName, age,credentials);

	}

}

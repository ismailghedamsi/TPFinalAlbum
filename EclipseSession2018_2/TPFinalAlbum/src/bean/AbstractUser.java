package bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author small44
 * Absr=tract classe representing normaluser and admin
 */
@Getter
@Setter
@ToString
public abstract class AbstractUser implements Comparable<AbstractUser>{
	private String firstName;
	private String lastName;
	private List<bean.Album> userAlbumCollection;
	private Credentials credentials;
	private int age;
	private int id;
	private static int compteur;

	/**
	 * Create an abstract user 
	 * @param firstName user's first name
	 * @param lastName user's last name
	 * @param age user's age
	 */
	public AbstractUser(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.id = compteur++;
		userAlbumCollection = new ArrayList<>();
	}
	
	/**
	 * Create a registered user 
	 * @param firstName registered user first name
	 * @param lastName registered user last name
	 * @param age registered user age
	 * @param credentials registered user credentials (login and  credential)
	 */
	public AbstractUser(String firstName, String lastName, int age,Credentials credentials) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.id = compteur++;
		this.credentials = credentials;
		userAlbumCollection = new ArrayList<>();
	}


	@Override
	public int compareTo(AbstractUser o) {
		// TODO Auto-generated method stub
		return o.firstName.compareTo(this.firstName);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((userAlbumCollection == null) ? 0 : userAlbumCollection.hashCode());
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
		AbstractUser other = (AbstractUser) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (userAlbumCollection == null) {
			if (other.userAlbumCollection != null)
				return false;
		} else if (!userAlbumCollection.equals(other.userAlbumCollection))
			return false;
		return true;
	}
	
}

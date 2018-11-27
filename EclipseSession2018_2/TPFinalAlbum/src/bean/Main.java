package bean;

public class Main {
	public static void main(String[] args) {
		AbstractUser admin = new NormalUser("","", 15,null);
		System.out.println(admin instanceof NormalUser);
	}
}

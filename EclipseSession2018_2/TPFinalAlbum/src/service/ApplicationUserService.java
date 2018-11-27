package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bean.AbstractUser;
import bean.Admin;
import bean.Credentials;
import bean.NormalUser;

/**
 * Service class for the application registered users
 * @author small44
 *
 */
public class ApplicationUserService {
	
	private static ApplicationUserService applicationUserService;
	//Begin singleton empletmentation
	private ApplicationUserService() {
		
	}
	
	/**
	 * Get the service that subscribe and authenticate users
	 * @return the singleton service
	 */
	public static synchronized ApplicationUserService getInstance() {
		if(applicationUserService == null) {
			applicationUserService = new ApplicationUserService();
		}
		return applicationUserService;
	}
	//End singleton empletmentation
	
	/**
	 * Register a new user to the application
	 * @param login user's login
	 * @param password user's password
	 * @param firstName user's firstname
	 * @param lastName user's lastname
	 * @param age user's age
	 * @return result of the user subscription
	 * @throws IOException file to load registered users list
	 */
	public  boolean subscribe(String login,String password,String firstName,String lastName,int age) throws IOException {
		boolean flag = false;
		AbstractUser user  ;
		if(login.equals("admin@gmail.com") && password.equals("admin")) {
			
			user = new Admin(firstName, lastName, age,new Credentials(login, password));
		}else {
			user = new NormalUser(firstName, lastName, age,new Credentials(login, password));
		}
		
		PersistanceService<AbstractUser> persistanceService = new PersistanceService<>();
		CrudService<AbstractUser> crudService = new CrudService<>();
		List<AbstractUser> userList;
		//If the user is the first user to subscribe to the website 
		if(FileUtils.readFileToString(new File(PersistanceService.USERS_LOCATION),"UTF-8").trim().isEmpty()) {
			userList = new ArrayList<>();
			crudService.addBean(userList, user);
			flag = persistanceService.saveBeans(PersistanceService.USERS_LOCATION, userList);
		}else {  
			//Add the new subscribed user to the list of registred users
			userList = (List<AbstractUser>) persistanceService.loadBeans(PersistanceService.USERS_LOCATION);
			crudService.addBean(userList, user);
			flag = persistanceService.saveBeans(PersistanceService.USERS_LOCATION, userList);
		}
		return flag;
		
	}
	
	/**
	 * User authentication process
	 * @param login user's login
	 * @param password user's password
	 * @return the registered user  or not if the user is not registered
	 */
	public  AbstractUser authentication(String login,String password) {
		AbstractUser authentifiedUser = null;
		List<AbstractUser> registredUsers = getRegistredUsers();
		for (AbstractUser abstractUser : registredUsers) {
			if(abstractUser.getCredentials().getUsername().equals(login) 
				&& abstractUser.getCredentials().getPassword().equals(password)) {
				authentifiedUser = abstractUser;
			}
		}
		
		return authentifiedUser;
	}
	
	/**
	 * Get the list of registered users
	 * @return the list of registered users
	 */
	public List<AbstractUser> getRegistredUsers(){
		PersistanceService<AbstractUser> persistanceService = new PersistanceService<>();
		return (List<AbstractUser>) persistanceService.loadBeans(PersistanceService.USERS_LOCATION);
		
	}
	
	
}

package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AbstractUser;
import bean.Admin;
import bean.Album;
import bean.Credentials;
import bean.NormalUser;
import service.ApplicationUserService;
import service.MailService;
import service.OperationService;
import service.PersistanceService;
import service.PropertiesFileIO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<AbstractUser> subscribedUsers;
	ApplicationUserService applicationUserService;
	AbstractUser authenticedUser =null;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationUserService applicationUserService = ApplicationUserService.getInstance();
		OperationService operationService = OperationService.getInstance();
		PersistanceService<bean.Album> persistanceService = new PersistanceService<>();
		 subscribedUsers = applicationUserService.getRegistredUsers();
		 
		String action = request.getParameter("action");
		if(action.equals("loginIn")) {
			String logInlogin = request.getParameter("userMail");
			String logInpassword = request.getParameter("userPassword");
			 authenticedUser = applicationUserService.authentication(logInlogin, logInpassword);
			System.out.println(authenticedUser);
			if(authenticedUser == null) {
				
				request.setAttribute("notMember", "You're not a member go subscribe ");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}else if(authenticedUser.getClass().getName().equals("bean.NormalUser")) {
				request.getSession().setAttribute("authenticedUser", authenticedUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("normalUserPage.jsp");
				dispatcher.forward(request, response);
			}else if(authenticedUser.getClass().getName().equals("bean.Admin")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
				dispatcher.forward(request, response);
			}
		}else if(action.equals("createAccount")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			int age = Integer.parseInt(request.getParameter("age"));
			String login = request.getParameter("email");
			String password = request.getParameter("password");
			applicationUserService.subscribe(login, password, firstName, lastName, age);
			PropertiesFileIO.saveMailProperties("/home/small44/000/"+lastName+firstName+".properties", login, password);
			MailService.sendMail("TPFinalAlbum", "You was subscribed to our website", "tpfinalalbum@gmail.com", login);
			response.sendRedirect("index.html");
			
		}else if(action.equals("addAlbumFromApi")){
				String albumName = request.getParameter("albumName");
				String artistName = request.getParameter("artistName");
				System.out.println(request.getSession().getAttribute("authenticedUser"));
				operationService.addAlbumToDatabaseViaApi((Admin)authenticedUser, artistName, albumName, PersistanceService.ALBUMS_LOCATION);
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
				dispatcher.forward(request, response);
			}else if(action.equals("displayApplicationAlbums")) {
				List<bean.Album> listeAlbum = (List<Album>) persistanceService.loadBeans(PersistanceService.ALBUMS_LOCATION);
				request.getSession().setAttribute("albumList", listeAlbum);
				RequestDispatcher dispatcher = request.getRequestDispatcher("aplicationAlbumList.jsp");
				dispatcher.forward(request, response);
				
			}else if(action.equals("supprimerAlbum")){
				System.out.println("ca rentre supprimerAlbum");
				String idAlbumToRemove = request.getParameter("idAlbum");
				operationService.removeAlbumFromApplicationDatabase((Admin)authenticedUser, idAlbumToRemove, PersistanceService.ALBUMS_LOCATION);
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

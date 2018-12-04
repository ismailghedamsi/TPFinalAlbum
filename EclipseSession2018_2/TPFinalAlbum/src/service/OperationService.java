package service;


import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;

import bean.*;
import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;

/**
 * Contain the core functionalities of the application
 * @author small44
 *
 */
public class OperationService {
	
	//public static final String ALBUMS_LOCATION = "/home/small44/0database/albums.xml"; 
	public static final String API_KEY = "31ad99437480e100e5f10c7a033f4700"; //this is the key used in the Last.fm API examples
	//Album with less of 10 track are usually EP more than 10 is an LP
	public static final int MAX_TRACK_EP = 10;
	
	
	
	private static PersistanceService<bean.Album> persistanceService = new PersistanceService<>();
	private static CrudService<bean.Album> crudService = new CrudService<>();
	
	private static PersistanceService<bean.Artist> persistanceServiceArtist=new PersistanceService<>();
	private static CrudService<bean.Artist> crudServiceArtist = new CrudService<>();
	private static OperationService operationService;
	private static PersistanceService<AbstractUser> persistanceServiceUser=new PersistanceService<>();
	private static CrudService<AbstractUser> crudServiceUser=new CrudService<>();
	private OperationService() {
		
	}
	
	public static synchronized OperationService getInstance() {
		if(operationService == null) {
			operationService = new OperationService();
		}
		return operationService;
	}
	

	/**
	 * Retrieve the album from the database
	 * @param artistName the artist who made the album
	 * @param albumName the album name
	 * @return the api album
	 */
	public  Album retrieveAlbumFromLastFmDatabase(String artistName, String albumName) {
		Album albumApi = Album.getInfo(artistName, albumName, API_KEY);
		return albumApi;
	}
	
	/**
	 * Create the application album based on the api album
	 * @param albumApi the album retrieved from the last fm database
	 * @param listeTrackAlbum the album tracklist 
	 * @return the builded application album
	 */
	public  bean.Album buildApplicationAlbum(Admin adder,Album albumApi,List<Song> listeTrackAlbum){
		bean.Album appAlbum = null;
		//Some album on the last fm api doesn't have an mbid so if it doesn't have one we genrate an id
		if(albumApi.getMbid() == null) {
			
			appAlbum = new bean.Album(RandomStringUtils.randomAlphanumeric(36),albumApi.getName(), getAlbumFormat(albumApi),listeTrackAlbum,adder.getFirstName());
		}else {
			appAlbum = new bean.Album(albumApi.getMbid(),albumApi.getName(), getAlbumFormat(albumApi),listeTrackAlbum,adder.getFirstName());
		}
		return appAlbum; 
	}
	

	/**
	 * Return the tracklist of the api album 
	 * @param albumApi the album retieved for the last fm database
	 * @return api album tracklist
	 */
	public  List<bean.Song> getTrackList(Album albumApi) {
		List<Track> listeTrackAlbumApi = (List<Track>) albumApi.getTracks();
		List<bean.Song> listeTrackAlbum = new ArrayList<>();
		for (Track track : listeTrackAlbumApi) {
			listeTrackAlbum.add(new bean.Song(track.getName()));
		}
		return listeTrackAlbum;
	}
	
	/**
	 * Add the album to the application database In our case it's an xml file and not a real database
	 * @param admin the admin who added the album to the application database
	 * @param artistName the application artist who made the album
	 * @param albumName the appplication album name
	 * @param albumLocation : the xml file that will contains all the application album
	 * @return operation result
	 */
	public  boolean addAlbumToDatabaseViaApi(Admin adder,String artistName,String albumName,String albumLocation) {
		Album albumApi = retrieveAlbumFromLastFmDatabase(artistName, albumName);
		
		List<bean.Song> listeTrackAlbum = getTrackList(albumApi);
		//Build the application album
		bean.Album albumApp = buildApplicationAlbum(adder,albumApi,listeTrackAlbum);
		//load the list of album available in the system
		 persistanceService = new PersistanceService<>();
		crudService = new CrudService<>();
		Collection<bean.Album> listeAlbums = persistanceService.loadBeans(albumLocation);
		listeAlbums.add(albumApp);
		//crudService.addBean(listeAlbums, albumApp);
		return persistanceService.saveBeans(albumLocation, listeAlbums);
	}
	
	
	public  boolean removeAlbumFromApplicationDatabase(Admin remover,String idAlbum,String albumLocation) {
		Collection<bean.Album> listeAlbums = persistanceService.loadBeans(albumLocation);
		bean.Album albumToRemove = findApplicationAlbumById(listeAlbums, idAlbum);
		System.out.println("Album to remove "+albumToRemove);
		return crudService.removeBean(listeAlbums, albumToRemove) && persistanceService.saveBeans(albumLocation, listeAlbums);
	}
	
	/**
	 * Add an artist to the application database(xml file)
	 * @param admin the admin who added the album
	 * @param artistName the artist name
	 * @param artistLocation the file that will store all the application artists
	 * @return the result of the operation
	 */
	public   boolean addArtistToDatabaseViaApi(Admin admin,String artistName,String artistLocation) {
		//Retrieve the artist from Last FM database
		Artist apiArtist = retrieveArtistFromLastFmDatabase(artistName);
		List<Album> listeAlbumArtistApi = new ArrayList<>(Artist.getTopAlbums(artistName, API_KEY));
		List<bean.Album> listeAlbumArtist = new ArrayList<>();
		for (Album album : listeAlbumArtistApi) {
			bean.Album albumApp = new bean.Album(album.getName(), "LP");
			listeAlbumArtist.add(albumApp);
		}
		bean.Artist artistApp = new bean.Artist(apiArtist.getName(),listeAlbumArtist);
	
		Collection<bean.Artist> listeArtists = persistanceServiceArtist.loadBeans(artistLocation);
		crudServiceArtist.addBean(listeArtists, artistApp);
		return persistanceServiceArtist.saveBeans(artistLocation, listeArtists);
	}
	
	/**
	 * Retrieve the artist from the database
	 * @param artistName the artist name to search
	 * @return the api artist
	 */
	public Artist retrieveArtistFromLastFmDatabase(String artistName) {
		return Artist.getInfo(artistName, API_KEY);
	}
	
	
	
	/**
	 * Add album to the user album collection
	 * @param databaseAlbumList the collection that contain the album list
	 * @param user The user who will add an album his collection
	 * @param albumName the name of the album to add to the collection
	 * @param saveLocation where the user album are stored
	 * @return the result of the operation
	 */
	public boolean addAlbumToUserAlbumCollection(String saveLocation,Collection<bean.Album> databaseAlbumList,AbstractUser user,String albumName) {
		boolean added = false;
		AbstractUser findedUser = findApplicationUser(user);
		 Collection<AbstractUser> usersList = persistanceServiceUser.loadBeans(PersistanceService.USERS_LOCATION);
		 usersList.remove(findedUser);
		//Add the album to user album collection if it exists in the application database (not last fm database)
			bean.Album findedAlbum = findApplicationAlbumByAlbumName(databaseAlbumList, albumName);
		 findedUser.getUserAlbumCollection().add(findedAlbum);
		 usersList.add(findedUser);
		if(findedAlbum != null ) {
			Collection<bean.Album> albumCollection  = findedUser.getUserAlbumCollection();
			crudService.addBean(albumCollection, findedAlbum);
			added = persistanceServiceUser.saveBeans(PersistanceService.USERS_LOCATION,usersList);
		}
		return added;
	}
	
	/**
	 * 
	 * @param user the user
	 * @return the finded user
	 */
	public AbstractUser findApplicationUser(AbstractUser user) {
		PersistanceService<AbstractUser> persitanceUser = new PersistanceService<>();
		CrudService<AbstractUser> crudUser = new CrudService<>();
		Collection<AbstractUser> users = persitanceUser.loadBeans(PersistanceService.USERS_LOCATION);
		AbstractUser findedUser =  crudUser.findBean(users, user);
		return findedUser;
	}
	
	/**
	 * Find an album from the application database(xml file) 
	 * @param databaseAlbumList the list of all the album available in the application
	 * @param albumName the name of the searched album
	 * @return the wanted album
	 */
	public bean.Album findApplicationAlbumById(Collection<bean.Album> databaseAlbumList,String id) {
		bean.Album researchedalbum = null;
		for (bean.Album album : databaseAlbumList) {
			if(album.getId().equals(id)) {
				researchedalbum = album;
				break;
			}else {
				throw new NoSuchElementException("Album not found");
			}
		}
		return researchedalbum;
	}
	
	public bean.Album findApplicationAlbumByAlbumName(Collection<bean.Album> databaseAlbumList,String albumName) {
		bean.Album researchedalbum = null;
		for (bean.Album album : databaseAlbumList) {
			if(album.getAlbumName().equals(albumName)) {
				researchedalbum = album;
				break;
			}else {
				throw new NoSuchElementException("Album not found");
			}
		}
		return researchedalbum;
	}
	
	/**
	 * Remove the album from the user collection
	 * @param user the user who want to remove the album from his collection
	 * @param album the album to remove
	 * @return the result of the operation
	 */
	public boolean removeAlbumFromUserAlbumCollection(AbstractUser user,bean.Album album) {
		boolean flag = false;
		AbstractUser findedUser = findApplicationUser(user);
		if(findedUser.getUserAlbumCollection() != null) {
			flag =  crudService.removeBean(user.getUserAlbumCollection(),album);
		}
		return flag;
	}
	
	
	
	/**
	 * Return the format of the album : LP or Ep
	 * @param apiAlbum the album from the last fm api
	 * @return the format of the album
	 */
	public   String getAlbumFormat(Album apiAlbum) {
		String format;
		if(apiAlbum.getTracks().size() < MAX_TRACK_EP) {
			 format = "EP";
		}else {
			format = "LP";
		}
		return format;
	}
	
	public static void main(String[] args) {
		OperationService op = OperationService.getInstance();
		op.addAlbumToDatabaseViaApi(new Admin("dffdfd", "gffgf", 24), "Tech N9ne", "Absolute Power", PersistanceService.ALBUMS_LOCATION);
	}
	
}

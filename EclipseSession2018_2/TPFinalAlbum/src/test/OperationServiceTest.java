package test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import bean.AbstractUser;
import bean.Admin;
import bean.Album;
import bean.Song;
import service.*;



public class OperationServiceTest {
	
	static OperationService operationService;
	PersistanceService<Album> persistanceService;
	Album albumApi;
	bean.Album albumApp;
	Admin admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	

	@Before
	public void setUp() throws Exception {
		operationService = OperationService.getInstance();
		persistanceService = new PersistanceService<>();
		admin = new Admin("Ismail", "Ghedamsi", 25);
	}

	@After
	public void tearDown() throws Exception {
		admin = null;
		operationService = null;
		persistanceService = null;
	}
	
	@Test
	public void testGetAlbumFormat() {
		assertEquals(operationService.getAlbumFormat(operationService.retrieveAlbumFromLastFmDatabase("Tech N9ne", "K.O.D")),"LP");
	}
	
	@Test
	public void testRetrieveAlbumLastFmDataBase() {
	
	//Trying to retrieve a non existent album
	//assertNull(OperationService.retrieveAlbumLastFmDataBase("", ""));
		
	assertNotNull(operationService.retrieveAlbumFromLastFmDatabase("Tech N9ne", "Absolute Power"));
	}
	
	@Test
	public void testGetTrackList() {
		assertNotNull(operationService.getTrackList(operationService.retrieveAlbumFromLastFmDatabase("Tech N9ne", "K.O.D")));
	}
	
	@Test
	public void testBuildApplicationAlbum() {
		de.umass.lastfm.Album albumApi = operationService.retrieveAlbumFromLastFmDatabase("Tech N9ne", "K.O.D");
		List<Song> trackList = operationService.getTrackList(albumApi);
		assertNotNull(operationService.buildApplicationAlbum(new Admin("ddffd", "", 24),albumApi, trackList));
	}
	

	@Test
	public void testAddAlbumToDatabaseViaApi() {
		assertTrue(operationService.addAlbumToDatabaseViaApi(admin, "Tech N9ne", "K.O.D", PersistanceService.ALBUMS_LOCATION));
	}

	@Test
	public void testFindApplicationAlbum() {
		Collection<bean.Album> listeAlnum = persistanceService.loadBeans(PersistanceService.ALBUMS_LOCATION);
		assertNotNull(OperationService.getInstance().findApplicationAlbum(listeAlnum,"K.O.D"));
	}
	
	@Test
	public void testAddAlbumToUserAlbumCollection() {
		Collection<bean.Album> listeAlnum = persistanceService.loadBeans(PersistanceService.ALBUMS_LOCATION);
		AbstractUser user = ApplicationUserService.getInstance().authentication("ismailghedamsi@gmail.com", "mypassword");
		OperationService.getInstance().addAlbumToUserAlbumCollection(PersistanceService.USERS_LOCATION,listeAlnum,admin , "Absolute Power");
	}
	
	

	@Test
	public void testAddArtistToDatabaseViaApi() {
		assertTrue(operationService.addArtistToDatabaseViaApi(admin, "Tech N9ne", PersistanceService.ALBUMS_LOCATION));
	}

	

	@Ignore
	public void testRemoveAlbumFromUserAlbumCollection() {
		operationService.addAlbumToDatabaseViaApi(admin, "Tech N9ne", "K.O.D", PersistanceService.ALBUMS_LOCATION);
		
	
		Collection<bean.Album> listeAlnum = persistanceService.loadBeans(PersistanceService.ALBUMS_LOCATION);
		OperationService.getInstance().addAlbumToUserAlbumCollection(PersistanceService.USERS_LOCATION,listeAlnum,admin , "Absolute Power");
		albumApp = operationService.findApplicationAlbum(listeAlnum, "Absolute Power");
		assertTrue(operationService.removeAlbumFromUserAlbumCollection(admin, albumApp));
	}
	
	@Test
	public void testRetrieveArtistFromLastFmDatabase() {
		assertNotNull(operationService.retrieveArtistFromLastFmDatabase("Tech N9ne"));
	}




}

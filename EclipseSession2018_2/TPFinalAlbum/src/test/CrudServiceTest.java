package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bean.Album;
import bean.NormalUser;
import service.CrudService;

public class CrudServiceTest {
	
	CrudService<bean.Album> crudService;
	Collection<bean.Album> listeAlbumDatabase;
	bean.AbstractUser user;
	bean.Album album1;
	bean.Album album2;

	

	@Before
	public void setUp() throws Exception {
		crudService = new CrudService<>();
		listeAlbumDatabase = new ArrayList<>();
		 user = new NormalUser("Ismail", "Ghedamis", 24,null);
		 album1 = new Album("Absolute Power", "LP");
		 album2 =  new Album("K.O.D", "LP") ;
	}

	@After
	public void tearDown() throws Exception {
		crudService =null;
		listeAlbumDatabase = null;
		 user = null;
		 album1 = null;
		 album2 =  null;
	}

	@Test
	public void testAddBean() {
		
		//assertTrue(crudService.addBean(listeAlbumDatabase, album1));
		assertFalse(crudService.addBean(null, album1));
	}
	
	@Test
	public void testFindBean() {
		crudService.addBean(listeAlbumDatabase, album1);
		assertNotNull(crudService.findBean(listeAlbumDatabase, album1));
	}

	@Test
	public void testGetBean() {
		crudService.addBean(listeAlbumDatabase, album1);
		assertNotNull(crudService.getBean(listeAlbumDatabase, 0));

	}
	
	@Test
	public void testContainsBean() {
		assertFalse(crudService.containsBean(listeAlbumDatabase, album1));
		
	}

	@Test
	public void testRemoveBean() {
		crudService.addBean(listeAlbumDatabase, album1);
		crudService.removeBean(listeAlbumDatabase, album1);
		assertFalse(crudService.containsBean(listeAlbumDatabase, album1));
	}

	

}

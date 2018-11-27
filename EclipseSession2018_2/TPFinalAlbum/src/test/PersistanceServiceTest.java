package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import bean.Album;
import service.PersistanceService;

public class PersistanceServiceTest {
	static PersistanceService<bean.Album> persistanceService;
	bean.Album album1;
	bean.Album album2;
	Collection<bean.Album> albumDatabaseList ;


	@Before
	public void setUp() throws Exception {
		persistanceService = new PersistanceService<>();
		album1 = new bean.Album("Absolute Power", "LP");
		album2 = new bean.Album("K.O.D", "LP");
		albumDatabaseList = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		persistanceService = null;
		album1 = null;
		album2 = null;
		albumDatabaseList = null;
	}

	@Test
	public void testSaveBeans() {
		
		albumDatabaseList.add(album1);
		albumDatabaseList.add(album2);
		assertTrue(persistanceService.saveBeans(PersistanceService.ALBUMS_LOCATION,albumDatabaseList ));
		
	}

	@Test
	public void testLoadBeans() {
		assertNotNull(persistanceService.loadBeans(PersistanceService.ALBUMS_LOCATION));
	}

}

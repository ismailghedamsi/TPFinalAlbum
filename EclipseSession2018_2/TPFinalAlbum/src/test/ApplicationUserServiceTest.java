package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.ApplicationUserService;

public class ApplicationUserServiceTest {

	ApplicationUserService service;
	@Before
	public void setUp() throws Exception {
		service = ApplicationUserService.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testSubscribe() {
		try {
			assertTrue(service.subscribe("ismailghedamsi@gmail.com", "mypassword","Ismail","Ghedamsi",24));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAuthentication() {
		assertNotNull(service.authentication("ismailghedamsi@gmail.com", "mypassword"));
	}
	



}

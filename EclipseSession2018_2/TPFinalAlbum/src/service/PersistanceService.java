package service;




import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bean.*;
import util.IPersistanceService;


/**
 * Service to save the application beans
 * @author small44
 *
 * @param <T> the type of the bean
 */
public class PersistanceService<T> implements IPersistanceService<T> {
	public static String XML_EXTENSION = ".xml";
	public static String  JSON_EXTENSION = ".json";
	public static final String ALBUMS_LOCATION = "/home/small44/0database/albums.xml"; 
	public static final String ARTISTS_LOCATION = "/home/small44/0database/artists.xml"; 
	public static final String USERS_LOCATION = "/home/small44/0database/users.xml";
	private  static final Logger LOGGER = java.util.logging.Logger.getLogger("PersistanceService.class");
	  
	@Override
	/**
	 * @inheritDoc
	 */
	public  boolean saveBeans(String fileName, Collection<T> c){
	
		XStream stream= null ;
		try {
			if(fileName==null || c==null) {
				return false;
			}
			if(fileName.endsWith(XML_EXTENSION)) {
				stream = new XStream(new DomDriver());
			}else if (fileName.endsWith(JSON_EXTENSION)) {
				stream = new XStream(new JettisonMappedXmlDriver());
				stream.setMode(XStream.NO_REFERENCES);
			}
			aliasing(stream);
			stream.toXML(c, new FileOutputStream(new File(fileName)));
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		
		
		return new File(fileName).exists();
	}
  
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<T> loadBeans(String fileName) {
		if(new File(fileName).length() == 0) {
			return Collections.emptyList();
		}
		XStream stream= null ;
		//On retourne une liste vide si le chargement n'a pas marcher pour eviter 
		// Le NullPointerException
		Collection<T> beans = new ArrayList<>();
		try {
			if(fileName.endsWith(XML_EXTENSION)) {
				stream = new XStream(new DomDriver());
			}else if (fileName.endsWith(JSON_EXTENSION)) {
				stream = new XStream(new JettisonMappedXmlDriver());
				stream.setMode(XStream.NO_REFERENCES);
			}
			
			aliasing(stream);
			beans = (Collection<T>) stream.fromXML(new FileInputStream(new File(fileName)));
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

		return beans;
	}
	
	/** Intern method so private
	 * Hide the structure of the project in the xml or json file from the client
	 * @param stream the xstream instance
	 */
	private void aliasing(XStream stream) {
		stream.alias("Album", bean.Album.class);
		stream.alias("Artist",bean.Artist.class);
		stream.alias("Song", bean.Song.class);
		stream.alias("NormalUser", NormalUser.class);
		stream.alias("Admin", Admin.class);
		stream.alias("AbstractUser", Admin.class);

	}
	

	
	
}

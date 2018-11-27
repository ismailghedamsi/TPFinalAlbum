package util;



import java.util.Collection;

/**
 * This interface is an persistance interface that is implemented to define the way data are stored
 * permanantly
 * @author small44
 *
 * @param <T> the type of object 
 */
public interface IPersistanceService<T> {
	
	
	/**
	 * Save the bean in a file depended on the extension
	 * Allowed extension : .json and .xml
	 * @param fileName The path name where the bean will be stored 
	 * @param col The collection that  will be parsed
	 * @return the success or failure of the saving 
	 */
	public boolean saveBeans(String fileName, Collection<T> col);
	
	/**
	 * Load the beans from a file
	 * Allowed extension : .json and .xml
	 * @param fileName the name of the file where the data will be loaded
	 * @return the collection that contains the data
	 */
	Collection<T> loadBeans(String fileName);
	
}

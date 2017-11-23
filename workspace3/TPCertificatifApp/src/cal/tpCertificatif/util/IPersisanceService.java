package cal.tpCertificatif.util;

import java.io.FileNotFoundException;


/**
 * Les methode epour la persistance des donnees
 * @author small44
 *
 * @param <T>
 */
public interface IPersisanceService<T> {
	public boolean saveElementToXML(String pathName,T elem);
	public T loadElement(String pathName) throws FileNotFoundException;
}
//todo

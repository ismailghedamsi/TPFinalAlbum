package cal.tpCertificatif.util;

import java.util.List;
/**
 * Les methodes du  crud
 * @author small44
 *
 * @param <T>
 */
public interface DAOUtil<T> {
	 T getElementsById(List<T> list,int id);
	 boolean deleteById(List<T> list,int id);
	 boolean add(List<T> list,T element);
	 T getRandomElement(List<T> list);
	 
}

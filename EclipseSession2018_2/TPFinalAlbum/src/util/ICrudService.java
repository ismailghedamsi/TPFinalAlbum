
package util;
import java.util.*;

/**
 * This interface define the operation of the C.R.U.D (Create,Read,Update,Delete)
 * @author small44
 *
 * @param <T> THe type of the elements that will be added to the collection
 */
public interface ICrudService<T> {
	/**C(Create) of the C.R.U.D
	 * Add a bean to a collection
	 * @param col The collection that will store the bean
	 * @param bean The bean that willbe stored in the bean
	 * @return if the operation succeded or not 
	 */
	boolean addBean(Collection<T> col,T bean);
	

	
	/**
	 * R(Read) of the C.R.U.D
	 * @param col  The collection where the bean is stored
	 * @param index The index of the collection
	 * @return the wanted bean
	 */
	T getBean(Collection<T> col,int index);
	
	/**
	 * R(Read) of the C.R.U.D
	 * @param col  The collection where the bean is stored
	 * @param bean the bean we search
	 * @return the wanted bean
	 */
	T findBean(Collection<T> col,T bean);
	
	
	/**
	 * 
	 * @param col The collection where the bean is stored
	 * @param bean The bean that we search
	 * @return true if the bean exist false otherwise
	 */
	boolean containsBean(Collection<T> col,T bean);
	
	
	/**
	 * The R(Remove) of the C.R.U.D
	 * @param col  The collection where the bean to delete is stored
	 * @param bean the bean to remove
	 * @return the success or failure of the delete operation
	 */
	boolean removeBean(Collection<T> col,T bean);
}

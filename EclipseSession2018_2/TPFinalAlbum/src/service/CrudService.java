package service;


import java.util.*;

import util.ICrudService;


/**
 * Crud service for the application beans
 * @author small44
 *
 * @param <T> the type of bean
 */
public class CrudService<T> implements ICrudService<T>{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addBean(Collection<T> col,T bean) {
		if(col == null) {
			return false;
		}
		return col.add(bean);
	
	}
    
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBean(Collection<T> col,int index ) {
		T bean=null;
		if(col instanceof List) {
			return ((List<T>)col).get(index);
		}else if(col instanceof Set) {
			bean = (T) new ArrayList<T>(col).get(index);
		}
		return bean;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeBean(Collection<T> col, T bean) {
		return col.remove(bean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsBean(Collection<T> col, T bean) {
		return col.contains(bean);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findBean(Collection<T> list, T bean) {
		ArrayList<T> arrayList = new ArrayList<T>(list);
		return arrayList.get(arrayList.indexOf(bean));
		
	}


}

package com.spring.boot.rest.service;

import java.util.List;

import com.spring.boot.rest.domain.Type;

/**
* Service interface class.
*  
* @author Simon Njenga
* @version 0.1
*/
public interface TypeService {

	/**
	 * Retrieves a list of all <code>Type</code> objects from the database.
	 * 
	 * @param <code>String</code>type, type object to retrieve.
	 * @return a <code>List</code> of <code>Type</code> objects
	 */
	List<Type> getTypeList(String type);
}

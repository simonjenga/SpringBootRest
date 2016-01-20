package com.spring.boot.rest.domain;

import java.io.Serializable;

/**
* A domain object interface to be implemented by all domain classes.
* 
* @author Simon Njenga
* @version 0.1
*/
public interface DomainObject extends Serializable {

	Long getId();
}

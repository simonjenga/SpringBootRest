package com.spring.boot.rest.domain;

import java.io.Serializable;

/**
 * A domain object interface to be implemented by all domain classes.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
public interface DomainObject extends Serializable {

    /**
     * Returns the primary key identifier.
     *
     * @return id Primary key identifier.
     */
    Long getId();

    /**
     * Sets the primary key identifier.
     *
     * WARNING: Setting the identifier is usually delegated to the database.
     * Implementations may throw an exception if you attempt to set this value manually.
     *
     * @param <code>Long</code>id, the Primary key identifier to set.
     */
    void setId(Long id);
}

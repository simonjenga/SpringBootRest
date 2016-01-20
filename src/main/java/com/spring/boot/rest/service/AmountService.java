package com.spring.boot.rest.service;

import java.util.List;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.service.exception.AmountDoesNotExist;

/**
 * Service interface class.
 *  
 * @author Simon Njenga
 * @version 0.1
 */
public interface AmountService {

    /**
     * Retrieves a list of all <code>Amount</code> objects from the database.
     * 
     * @param <code>Long</code>id, id of the <code>Amount</code> object to retrieve.
     * @return a <code>List</code> of <code>Amount</code> objects
     * @throws <code>AmountDoesNotExist</code> if the <code>Amount</code>
     *  object is not found.
     */
    List<Amount> getAmountList(Long id) throws AmountDoesNotExist;

}

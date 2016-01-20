package com.spring.boot.rest.service;

import java.util.List;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;
import com.spring.boot.rest.service.exception.TransactionDoesNotExist;

/**
* Service interface class.
*  
* @author Simon Njenga
* @version 0.1
*/
public interface TransactionService {

	/**
	 * Persists a Transaction object to the database. 
	 *
	 * @param <code>Transaction</code>transaction, Transaction object to persist.
	 * @return a <code>Transaction</code> object.
	 * @throws <code>TransactionAlreadyExists</code> if the <code>Transaction</code>
	 *  object is not successfully persisted.
	 */
	Transaction saveTransaction(Transaction transaction)
			throws TransactionAlreadyExists;

	/**
	 * Updates a Transaction object in the database. 
	 *
	 * @param <code>Transaction</code>transaction, Transaction object to update.	 
	 * @param <code>Long</code>id, id of the object to update.
	 * @return a <code>Transaction</code> object.
	 * @throws <code>TransactionDoesNotExist</code> if the <code>Transaction</code>
	 *  object is not successfully updated.
	 */
	Transaction updateTransaction(Long id, Transaction transaction)
			throws TransactionDoesNotExist;

	/**
	 * Retrieves a list of all <code>Transaction</code> objects from the database.
	 * 
	 * @return a <code>List</code> of <code>Transaction</code> objects
	 */
	List<Transaction> getTransactionList();
}

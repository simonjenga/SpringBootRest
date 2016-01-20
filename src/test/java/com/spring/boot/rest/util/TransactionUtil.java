package com.spring.boot.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.domain.Parent;
import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.domain.Type;

/**
 * This utility class helps to create a {@link Transaction} object.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
public class TransactionUtil {

	private static List<Amount> amounts = new ArrayList<Amount>();	
	private static List<Type> types = new ArrayList<Type>();
	private static List<Parent> parents = new ArrayList<Parent>();
	
	private static Transaction transaction =  new Transaction(1L, amounts, types, parents);
	
	// Suppresses default constructor, ensuring non-instantiability.
	private TransactionUtil() {
		Amount amount = new Amount();
		amount.setId(1L);
		amount.setAmount(10D);
		amount.setTransaction(transaction);
		amounts.add(amount);
		
		Type type = new Type();
		type.setId(1L);
		type.setType("cars");
		type.setTransaction(transaction);
		types.add(type);
		
		Parent parent = new Parent();
		parent.setId(1L);
		parent.setParent_id(1L);
		parent.setTransaction(transaction);	
		parents.add(parent);
	}
	
	/**
     * Creates and returns a single transaction.
     */
	public static Transaction createTransaction() {
        return transaction;
    }
	
	/**
     * Creates and returns a list of transactions.
     */
	public static List<Transaction> createTransactionList(int howMany) {
        List<Transaction> transList = new ArrayList<Transaction>();
        for (int i = 0; i < howMany; i++) {
        	transList.add(new Transaction(Long.valueOf(i), amounts, types, parents));
        }
        return transList;
    }
}

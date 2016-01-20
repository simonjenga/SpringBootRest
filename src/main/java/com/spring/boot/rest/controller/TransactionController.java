package com.spring.boot.rest.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.service.TransactionService;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;
import com.spring.boot.rest.service.exception.TransactionDoesNotExist;

/**
* This controller class handles all REST requests about entities for {@link Transaction}
*  
* @author Simon Njenga
* @version 0.1
*/
@RestController
@RequestMapping(value = "/transactionservice")
public class TransactionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    @Inject
    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    // save a transaction
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    @ResponseBody 
    public Transaction addTransaction(@RequestBody @Valid final Transaction transaction) {
    	LOGGER.debug("Received request to create the {}", transaction);
    	try {
    		return transactionService.saveTransaction(transaction);
		} catch (TransactionAlreadyExists e) {
			throw new IllegalStateException(e);
		}        
    } 
    
    // update a transaction
    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Transaction putTransaction(@PathVariable("id") String id, @RequestBody @Valid final Transaction transaction) {
    	LOGGER.debug("Received request to put the {}", transaction);
        try {
        	return transactionService.updateTransaction(Long.valueOf(id), transaction);
		} catch (TransactionDoesNotExist e) {
			throw new IllegalStateException(e);
		}
    }
    
    // list all transactions
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> listTransactions() {
        LOGGER.debug("Received request to list all transactions");
        return transactionService.getTransactionList();
    }
    
	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(TransactionAlreadyExists e) {
        return e.getMessage();
    }
}

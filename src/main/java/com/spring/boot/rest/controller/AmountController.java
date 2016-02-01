package com.spring.boot.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.service.AmountService;
import com.spring.boot.rest.service.exception.AmountDoesNotExist;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;

/**
 * This controller class handles all REST requests about entities for {@link Amount}
 *  
 * @author Simon Njenga
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/transactionservice")
public class AmountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmountController.class);
    private final AmountService amountService;

    @Inject
    public AmountController(final AmountService amountService) {
        this.amountService = amountService;
    }
    
    // a total of transactions that are transitively linked by their parent_id to transaction_id
    @RequestMapping(value = "/sum/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Double sumOfAllTransactions(@PathVariable("id") String id) {
        LOGGER.debug("Received request to sum all amounts by transaction ID");        
        try {
            List<Amount> amounts = amountService.getAmountList(Long.parseLong(id));

            Double addedAmountInTotal = 0D;

            // add all the amounts together for all the transactions
            for (int i = 0; i < amounts.size(); i++) {
            	addedAmountInTotal = (addedAmountInTotal + amounts.get(i).getAmount());
            }
            return addedAmountInTotal;
        } catch (AmountDoesNotExist e) {
            throw new IllegalStateException(e);
        }
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(TransactionAlreadyExists e) {
        return e.getMessage();
    }
}

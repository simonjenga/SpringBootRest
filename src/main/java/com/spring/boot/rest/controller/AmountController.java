package com.spring.boot.rest.controller;

import java.util.LinkedList;
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
import com.spring.boot.rest.domain.Parent;
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
@RequestMapping(value = "/sum")
public class AmountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmountController.class);
    private final AmountService amountService;

    @Inject
    public AmountController(final AmountService amountService) {
        this.amountService = amountService;
    }
    
    // a total of transactions that are transitively linked by their parent_id to transaction_id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Double listTransactionIDs(@PathVariable("id") String id) {
        LOGGER.debug("Received request to list all transaction IDs");        
        try {
            List<Amount> amounts = amountService.getAmountList(Long.parseLong(id));
            List<Parent> parentIDs = new LinkedList<Parent>();

            Double totalAmount = 0D;

            for (int i = 0; i < amounts.size(); i++) {
                totalAmount = totalAmount + amounts.get(i).getTransaction().getAmount().get(i).getAmount();
                parentIDs.add(amounts.get(i).getTransaction().getParent_id().get(i));
            }

            for (int i = 0; i < parentIDs.size(); i++) {
                totalAmount = totalAmount + parentIDs.get(i).getTransaction().getAmount().get(i).getAmount();
            }

            return totalAmount;
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

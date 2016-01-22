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

import com.spring.boot.rest.domain.Type;
import com.spring.boot.rest.service.TypeService;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;
import com.spring.boot.rest.util.TransactionUtil;

/**
 * This controller class handles all REST requests about entities for {@link Type}
 *  
 * @author Simon Njenga
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/transactionservice")
public class TypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeController.class);
    private final TypeService typeService;

    @Inject
    public TypeController(final TypeService typeService) {
        this.typeService = typeService;
    }
    
    // list of all transaction ids that share the same type
    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> listTransactionIDs(@PathVariable("type") String type) {
        LOGGER.debug("Received request to list all transaction IDs");
        List<Type> types = typeService.getTypeList(type);
        List<Long> tranactionIDs = new LinkedList<Long>();
        for (int i = 0; i < types.size(); i++) {
            if (TransactionUtil.transactionNotNull(types.get(i).getTransaction())) {
                tranactionIDs.add(types.get(i).getTransaction().getId());
            }
        }
        return tranactionIDs;
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(TransactionAlreadyExists e) {
        return e.getMessage();
    }
}

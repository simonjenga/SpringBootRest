package com.spring.boot.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.repository.TransactionRepository;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;
import com.spring.boot.rest.service.exception.TransactionDoesNotExist;

/**
 * Service implementation class for {@link TransactionService}
 *  
 * @author Simon Njenga
 * @version 0.1
 */
@Service
@Validated
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository repository;

    @Inject
    public TransactionServiceImpl(final TransactionRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional(readOnly = false, rollbackFor = TransactionAlreadyExists.class)
    public Transaction saveTransaction(@NotNull @Valid final Transaction transaction)
        throws TransactionAlreadyExists {
        LOGGER.debug("Creating {}", transaction);
        Transaction existing = repository.getOne(transaction.getId());
        if (existing != null) {
            throw new TransactionAlreadyExists(
                String.format("There already exists a transaction with id=%s", transaction.getId()));
        }
        return repository.save(transaction);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = TransactionDoesNotExist.class)
    public Transaction updateTransaction(Long id, @NotNull @Valid final Transaction transaction) 
        throws TransactionDoesNotExist {
        LOGGER.debug("Creating {}", transaction);
        Transaction existing = repository.getOne(id);
        if (existing == null) {
            throw new TransactionDoesNotExist(String.format("There is not a transaction with id=%s", id));
        }
        existing.setAmount(transaction.getAmount());    	
        existing.setType(transaction.getType());
        existing.setParent_id(transaction.getParent_id());
        return repository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionList() {
        LOGGER.debug("Retrieving the list of all transactions");
        return repository.findAll();
    }
}

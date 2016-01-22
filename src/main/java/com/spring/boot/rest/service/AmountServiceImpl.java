package com.spring.boot.rest.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.repository.AmountRepository;
import com.spring.boot.rest.service.exception.AmountDoesNotExist;

/**
 * Service implementation class for {@link AmountService}
 *  
 * @author Simon Njenga
 * @version 0.1
 */
@Service
@Validated
public class AmountServiceImpl implements AmountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmountServiceImpl.class);
    private final AmountRepository repository;

    @Inject
    public AmountServiceImpl(final AmountRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Amount> getAmountList(Long id) throws AmountDoesNotExist {
        Amount existing = repository.findOne(id);
        if (existing == null) {
            throw new AmountDoesNotExist(
                String.format("There is not an amount with id=%s", id));
        }
        List<Amount> amounts = existing.getTransaction().getAmount();    	
        return amounts;
    }
}

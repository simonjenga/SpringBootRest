package com.spring.boot.rest.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.repository.AmountRepository;
import com.spring.boot.rest.service.exception.AmountDoesNotExist;
import com.spring.boot.rest.util.AmountUtil;
import com.spring.boot.rest.util.TransactionUtil;

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
        LOGGER.debug("Retrieving{} the list of all transactions by ID:", id);

        Optional<Amount> existingOptional = this.repository.findById(id);
        Amount existing = null;

        if (existingOptional != null && existingOptional.isPresent()) {
            existing = existingOptional.get();
        } else {
            throw new AmountDoesNotExist(String.format("There is not an amount with id=%s", id));
        }

        List<Amount> totalAmounts = new LinkedList<Amount>();

        // get amounts for all transactions that are NOT transitively linked by their parent_id to transaction_id
        for (int i = 0; i < existing.getTransaction().getAmount().size(); i++) {
            if (TransactionUtil.transactionNotNull(existing.getTransaction())) {
                Amount amount = existing.getTransaction().getAmount().get(i);
                AmountUtil.amountNotNull(amount, totalAmounts);
            }
        }

        // get amounts for all transactions that ARE transitively linked by their parent_id to transaction_id
        for (int i = 0; i < existing.getTransaction().getParent_id().get(i).getTransaction().getAmount().size(); i++) {
            if (TransactionUtil.transactionNotNull(existing.getTransaction().getParent_id().get(i).getTransaction())) {
                Amount amount = existing.getTransaction().getParent_id().get(i).getTransaction().getAmount().get(i);
                AmountUtil.amountNotNull(amount, totalAmounts);
            }
        }
        return totalAmounts;
    }
}

package com.spring.boot.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A domain object class that models an amount in a transaction.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "amount")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Amount implements DomainObject {

    private static final long serialVersionUID = 3601014692875167029L;

    @Id
    @NotNull
    @Column(name = "amount_id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    /**
     * Public default constructor
     */
    public Amount() {		
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}

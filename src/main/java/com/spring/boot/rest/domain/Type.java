package com.spring.boot.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A domain object class that models a type in a transaction.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type implements DomainObject {

    private static final long serialVersionUID = -8063253696029932643L;

    @Id
    @NotNull
    @Column(name = "type_id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Size(max = 64)
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    /**
     * Public default constructor
     */
    public Type() {
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

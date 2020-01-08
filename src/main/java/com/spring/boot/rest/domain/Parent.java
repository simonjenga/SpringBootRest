package com.spring.boot.rest.domain;

import javax.persistence.Basic;
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
 * A domain object class that models a parent in a transaction.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "parents")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parent implements DomainObject {

    private static final long serialVersionUID = 7229794163940525089L;

    @Id
    @NotNull
    @Column(name = "parent_id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Basic(optional = true)
    @Column(name = "parent_id", nullable = true, updatable = false, insertable = false)
    private Long parent_id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    /**
     * Public default constructor
     */
    public Parent() {	
    }    

    /**
     * @return the parent_id
     */
    public Long getParent_id() {
        return parent_id;
    }

    /**
     * @param parentId the parent_id to set
     */
    public void setParent_id(Long parentId) {
        parent_id = parentId;
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
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

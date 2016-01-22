package com.spring.boot.rest.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A domain object class that models a transaction.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements DomainObject {

    private static final long serialVersionUID = 6418865324997568989L;

    @Id
    @NotNull
    @Column(name = "transaction_id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Amount> amount;

    @NotNull
    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Type> type;

    @NotNull
    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parent> parent_id;

    /**
     * Public default constructor
     */
    public Transaction() {		
    }

    /**
     * Public constructor
     */
    public Transaction(Long id, List<Amount> amount, List<Type> type, List<Parent> parent_id) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parent_id = parent_id;
    }

    /**
     * @return the amount
     */
    public List<Amount> getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(List<Amount> amount) {
        this.amount = amount;
    }

    /**
     * @return the type
     */
    public List<Type> getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(List<Type> type) {
        this.type = type;
    }

    /**
     * @return the parent_id
     */
    public List<Parent> getParent_id() {
        return parent_id;
    }

    /**
     * @param parentId the parent_id to set
     */
    public void setParent_id(List<Parent> parentId) {
        parent_id = parentId;
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

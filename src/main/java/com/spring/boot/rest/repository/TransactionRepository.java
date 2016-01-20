package com.spring.boot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.rest.domain.Transaction;

/**
 * Spring Data JPA provides a repository programming model that starts with
 * an interface per managed domain object.
 * 
 * Defining this interface serves two purposes:
 * 
 * First, by extending {@link JpaRepository} we get a bunch of generic CRUD methods into
 * our type that allows saving {@link Transaction}s, deleting them and so on.
 * 
 * Second, this will allow the Spring Data JPA repository infrastructure to scan
 * the classpath for this interface and create a Spring bean for it.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
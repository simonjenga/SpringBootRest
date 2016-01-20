package com.spring.boot.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.spring.boot.rest.domain.Type;
import com.spring.boot.rest.repository.TypeRepository;

/**
* Service implementation class for {@link TypeService}
*  
* @author Simon Njenga
* @version 0.1
*/
@Service
@Validated
public class TypeServiceImpl implements TypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TypeServiceImpl.class);
	private final TypeRepository repository;

    @Inject
    public TypeServiceImpl(final TypeRepository repository) {
        this.repository = repository;
    }

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Type> getTypeList(String type) {
		LOGGER.debug("Retrieving {}", type);
		TypedQuery<Type> query = em.createQuery("select transaction from Type t where t.type = ?1", Type.class);
		query.setParameter(1, type);
		return query.getResultList();
	}
}

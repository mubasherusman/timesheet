package com.mubasher.timesheet.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.mubasher.timesheet.dao.GenericJpaRepository;

@NoRepositoryBean
public class GenericJpaRepositoryImpl<T, ID extends Serializable> implements GenericJpaRepository<T, ID>{
	private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";
	
	@PersistenceContext
	protected EntityManager em;
	
	protected JpaEntityInformation<T, ?> entityInformation;
	
	
	public GenericJpaRepositoryImpl(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	@Transactional
	public void flush() {
		em.flush();
	}

	@Override
	public T saveAndFlush(T entity) {
		T result = save(entity);
		flush();
		return result;
	}

	@Override
	public T getOne(ID id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);
		return em.getReference(getDomainClass(), id);
	}

	@Override
	public T save(T entity) {
		if (isNew(entity)) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}
	}


	private boolean isNew(T entity) {
		
		return !em.contains(entity);
	}
	
	protected Class<T> getDomainClass() {
		return entityInformation.getJavaType();
	}

	@Override
	public T findById(ID id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);
		return em.find(getDomainClass(), id);
	}

	
	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "The entity must not be null!");
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		Assert.notNull(entities, "The given Iterable of entities not be null!");

		for (T entity : entities) {
			delete(entity);
		}
		
	}

	@Override
	public boolean existsById(ID id) {
		return findById(id)!=null;
	}

	@Override
	public void deleteById(ID id) {
		delete(findById(id));
		
	}

	@Override
	public T findByExample(T entity) {
		
		throw new NotImplementedException();
	}


}

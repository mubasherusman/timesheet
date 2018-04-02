package com.mubasher.timesheet.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly=false)
public class GenericSimpleJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericSimpleJpaRepository<T, ID>{
	
	private final EntityManager entityManager;
	
	Class type;

	public GenericSimpleJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,  EntityManager entityManager){
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}
	
	public GenericSimpleJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	@Override
	public T handleCustom(String id) {
		Annotation[] ann = type.getAnnotations();
		return null;
	}

}

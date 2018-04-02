package com.mubasher.timesheet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.WorkTypeRepository;
import com.mubasher.timesheet.model.WorkType;

@Repository("workTypeRepository")
@Transactional(readOnly=false,isolation = Isolation.READ_UNCOMMITTED)
public class WorkTypeRepositoryImpl extends GenericJpaRepositoryImpl<WorkType, Integer> implements WorkTypeRepository{

	public WorkTypeRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityInformation = JpaEntityInformationSupport.getEntityInformation(WorkType.class, entityManager);
	}

	@Override
	public List<WorkType> findAll() {
		Query q= em.createNativeQuery("SELECT * FROM work_type", WorkType.class);
		@SuppressWarnings("unchecked")
		List<WorkType> l = q.getResultList();
		return l;
	}
	
	public WorkType findByExample(WorkType entity) {
		
		Query q= em.createNativeQuery("SELECT * FROM work_type where id = ?1", WorkType.class);
		q.setParameter(1, entity.getId());
		return (WorkType) q.getSingleResult();
	}

}

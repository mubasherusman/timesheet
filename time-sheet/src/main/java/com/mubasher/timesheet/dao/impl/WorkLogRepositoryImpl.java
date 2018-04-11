package com.mubasher.timesheet.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.WorkLogRepository;
import com.mubasher.timesheet.model.Work;

@Repository("workLogRepository")
@Transactional
public class WorkLogRepositoryImpl extends GenericJpaRepositoryImpl<Work, Integer> implements WorkLogRepository{

	public WorkLogRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityInformation = JpaEntityInformationSupport.getEntityInformation(Work.class, entityManager);
	}

	@Override
	public List<Work> findByUserId(Integer id) {
		Query q= em.createNativeQuery("SELECT * FROM work_logs where user_id = ?1 ", Work.class);
		q.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Work> l = q.getResultList();
		return l;
	}

	@Override
	public List<Work> findAll() {
		Query q= em.createNativeQuery("SELECT * FROM work_logs", Work.class);
		@SuppressWarnings("unchecked")
		List<Work> l = q.getResultList();
		return l;
	}

	@Override
	public List<Work> findByUserIdAndWeek(int id, int week) {
		Query q= em.createNativeQuery("SELECT * FROM work_logs where user_id = ?1 AND week = ?2 ", Work.class);
		q.setParameter(1, id);
		q.setParameter(2, week);
		@SuppressWarnings("unchecked")
		List<Work> l = q.getResultList();
		return l;
	}

}

package com.mubasher.timesheet.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.RoleRepository;
import com.mubasher.timesheet.model.Role;

@Repository("roleRepository")
@Transactional(readOnly=false,isolation = Isolation.READ_UNCOMMITTED)
public class RoleRepositrotyImpl extends GenericJpaRepositoryImpl<Role, Integer> implements RoleRepository{

	public RoleRepositrotyImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityInformation = JpaEntityInformationSupport.getEntityInformation(Role.class, entityManager);
	}



	@Override
	public Role findByRole(String role) {
		Query q= em.createNativeQuery("SELECT * FROM role where role = ?1 ", Role.class);
		q.setParameter(1, role);
		return  (Role) q.getSingleResult();
	}

	
}

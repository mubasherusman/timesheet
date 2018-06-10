package com.mubasher.timesheet.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.UserRepository;
import com.mubasher.timesheet.model.User;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl extends GenericJpaRepositoryImpl<User, Integer> implements UserRepository{

	public UserRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityInformation = JpaEntityInformationSupport.getEntityInformation(User.class, entityManager);
	}

	@Override
	public User findByEmail(String email) {
		Query q= em.createNativeQuery("SELECT * FROM user where email = ?1 ", User.class);
		q.setParameter(1, email);
		return  (User) q.getSingleResult();
	}


}

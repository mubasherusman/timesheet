package com.mubasher.timesheet.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.model.User;


@Repository("userRepository")
@Transactional(readOnly=false)
public interface UserRepository extends GenericSimpleJpaRepository<User, Long> {
	 User findByEmail(String email);
}
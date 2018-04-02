package com.mubasher.timesheet.dao;

import com.mubasher.timesheet.model.User;

public interface UserRepository extends GenericJpaRepository<User, Integer> {
	 User findByEmail(String email);
}
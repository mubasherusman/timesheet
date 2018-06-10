package com.mubasher.timesheet.service;

import com.mubasher.timesheet.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User saveUser(User user);
}

package com.mubasher.timesheet.service.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.RoleRepository;
import com.mubasher.timesheet.dao.UserRepository;
import com.mubasher.timesheet.model.Role;
import com.mubasher.timesheet.model.User;
import com.mubasher.timesheet.service.UserService;


@Service("userService")
@Transactional(readOnly=false,isolation = Isolation.READ_UNCOMMITTED)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		User u = userRepository.save(user);
		return u;
	}

}

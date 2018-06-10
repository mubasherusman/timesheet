package com.mubasher.timesheet.dao;


import com.mubasher.timesheet.model.Role;

public interface RoleRepository extends GenericJpaRepository<Role, Integer>{
	Role findByRole(String role);

}

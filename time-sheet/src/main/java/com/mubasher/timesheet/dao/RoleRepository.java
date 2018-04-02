package com.mubasher.timesheet.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.model.Role;


@Repository("roleRepository")
@Transactional(readOnly=false)
public interface RoleRepository extends GenericSimpleJpaRepository<Role, Integer>{
	Role findByRole(String role);

}

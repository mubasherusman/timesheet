package com.mubasher.timesheet.dao;

import java.util.List;

import com.mubasher.timesheet.model.WorkType;

public interface WorkTypeRepository extends GenericJpaRepository<WorkType, Integer>{

	List<WorkType> findAll();

}

package com.mubasher.timesheet.dao;

import java.util.List;

import com.mubasher.timesheet.model.Work;


public interface WorkLogRepository extends GenericJpaRepository<Work, Integer>{

	List<Work> findByUserId(Integer id);

	List<Work> findAll();

}

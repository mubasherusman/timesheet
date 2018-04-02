package com.mubasher.timesheet.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.model.WorkType;

@Repository("workTypeRepository")
@Transactional(readOnly=false)
public interface WorkTypeRepository extends GenericSimpleJpaRepository<WorkType, Integer>{

}

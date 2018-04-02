package com.mubasher.timesheet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.model.Work;

@Repository("workLogRepository")
@Transactional(readOnly=false)
public interface WorkLogRepository extends GenericSimpleJpaRepository<Work, Integer>{

	List<Work> findByUserId(Integer id);

}

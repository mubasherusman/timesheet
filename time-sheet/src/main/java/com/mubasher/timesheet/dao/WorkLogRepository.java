package com.mubasher.timesheet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mubasher.timesheet.model.Work;

@Repository("workLogRepository")
public interface WorkLogRepository extends JpaRepository<Work, Long>{

	List<Work> findByUserId(Integer id);

}

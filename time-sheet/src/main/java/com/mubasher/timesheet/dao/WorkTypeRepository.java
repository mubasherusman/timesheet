package com.mubasher.timesheet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mubasher.timesheet.model.WorkType;

@Repository("workTypeRepository")
public interface WorkTypeRepository extends JpaRepository<WorkType, Long>{

}

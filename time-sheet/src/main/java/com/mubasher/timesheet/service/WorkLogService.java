package com.mubasher.timesheet.service;

import java.util.List;

import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.model.WorkType;


public interface WorkLogService {

	Work logWork(Work work);

	void deleteWork(Work w);

	Work findWork(Work w);

	Work findWork(Integer id);
	
	List<Work> findWorks();

	List<WorkType> findWorkTypes();

	int logWorks(List<Work> works);

	List<Work> findWorksByUserId(int id);

}

package com.mubasher.timesheet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.WorkLogRepository;
import com.mubasher.timesheet.dao.WorkTypeRepository;
import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.model.WorkType;
import com.mubasher.timesheet.service.WorkLogService;

@Service
@Transactional(readOnly=false,isolation = Isolation.READ_UNCOMMITTED)
public class WorkLogServiceImpl implements WorkLogService{

	@Autowired
	private WorkLogRepository workLogRepository;
	
	@Autowired
	private WorkTypeRepository workTypeRepository;
	
	@Override
	public List<WorkType> findWorkTypes() {
		return workTypeRepository.findAll();
	}
	
	@Override
	public int logWorks(List<Work> works) {
		if(works == null || works.size() == 0)
			throw new IllegalArgumentException("List of Works required.");
		int count = 0;
		for(Work w : works) {
			Work loggedWork = logWork(w);
			if(loggedWork.getId()>0) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public Work logWork(Work work) {
		WorkType wt= workTypeRepository.findByExample(work.getWorkType());
		work.setWorkType(wt);
		return workLogRepository.saveAndFlush(work);
	}

	@Override
	public void deleteWork(Work w) {
		workLogRepository.delete(w);
	}

	@Override
	public Work findWork(Work w) {
		return findWork(w.getId());
	}

	@Override
	public Work findWork(Integer id) {
		Work o =  workLogRepository.findById(id);
		return o;
	}

	@Override
	public List<Work> findWorks() {
		return workLogRepository.findAll();
	}

	@Override
	public List<Work> findWorksByUserId(int id) {
		return workLogRepository.findByUserId(id);
	}
	
	

}

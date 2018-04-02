package com.mubasher.timesheet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mubasher.timesheet.dao.WorkLogRepository;
import com.mubasher.timesheet.dao.WorkTypeRepository;
import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.model.WorkType;
import com.mubasher.timesheet.service.WorkLogService;

@Service
public class WorkLogServiceImpl implements WorkLogService{

	@Autowired
	private WorkLogRepository workLogRepository;
	
	@Autowired
	private WorkTypeRepository workTypeRepository;
	
	@Override
	@Transactional
	public List<WorkType> findWorkTypes() {
		return workTypeRepository.findAll();
	}
	
	@Override
	@Transactional
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
	@Transactional
	public Work logWork(Work work) {
		return workLogRepository.save(work);
	}

	@Override
	@Transactional
	public void deleteWork(Work w) {
		workLogRepository.delete(w);
	}

	@Override
	@Transactional
	public Work findWork(Work w) {
		return findWork(w.getId());
	}

	@Override
	@Transactional
	public Work findWork(Integer id) {
		Optional<Work> o =  workLogRepository.findById(id);
		return o.get();
	}

	@Override
	@Transactional
	public List<Work> findWorks() {
		return workLogRepository.findAll();
	}

	@Override
	public List<Work> findWorksByUserId(int id) {
		return workLogRepository.findByUserId(id);
	}
	
	

}

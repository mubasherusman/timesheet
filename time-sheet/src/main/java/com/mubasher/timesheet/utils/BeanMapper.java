package com.mubasher.timesheet.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mubasher.timesheet.controller.dto.UserDetails;
import com.mubasher.timesheet.controller.dto.WorkLog;
import com.mubasher.timesheet.model.User;
import com.mubasher.timesheet.model.Work;


public class BeanMapper {
	
	public static Work map(WorkLog workLog) {
		if(workLog == null)
			throw new IllegalArgumentException("WorkLog is null");
		
		Date date = workLog.getCurrentDate();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		User user = new User();
		user.setEmail(workLog.getUserEmail());
		
		Work work = new Work(user , date,workLog.getJiraId() , workLog.getDescription(), workLog.getWorkType(), workLog.getHour(), week);
		
		return work;
		
	}
	
	public static WorkLog map(Work work) {
		WorkLog
			wl = new WorkLog();
		wl.setCurrentDate(work.getDate());
		User u = work.getUser();
		if(u!=null) {
			wl.setUserEmail(u.getEmail());
		}
		wl.setDescription(work.getDescription());
		wl.setHour(work.getHours());
		wl.setJiraId(work.getJiraId());
		wl.setWorkType(work.getWorkType());
		return wl;
		
	}

	public static List<WorkLog> mapWork(List<Work> works) {		
		List<WorkLog> wl= new ArrayList<WorkLog>();
		for(Work w : works) {
			wl.add(map(w));
		}
		return wl;
	}

	public static List<Work> mapWorkLogs(List<WorkLog> workLogs) {
		if(workLogs == null || workLogs.size()==0)
			throw new IllegalArgumentException("Work Logs are required");
		
		List<Work> w= new ArrayList<Work>();
		for(WorkLog wl : workLogs) {
			w.add(map(wl));
		}
		return w;
	}
	
	public static UserDetails map(User user) {
		UserDetails ud = new UserDetails();
		ud.setActive(user.getActive());
		ud.setName(user.getName());
		ud.setEmail(user.getEmail());
		return ud;
	}
	
	public static User map(UserDetails ud) {
		User u = new User();
		u.setName(ud.getName());
		u.setId(ud.getId());
		u.setActive(ud.getActive());
		return u;
	}

}
package com.mubasher.timesheet.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mubasher.timesheet.controller.dto.LogEntry;
import com.mubasher.timesheet.controller.dto.UserDetails;
import com.mubasher.timesheet.controller.dto.WorkLog;
import com.mubasher.timesheet.model.User;
import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.model.WorkType;


public class BeanMapper {
	
	public static List<Work> map(WorkLog workLog) {
		if(workLog == null)
			throw new IllegalArgumentException("WorkLog is null");
		if(workLog.getLogEntries() == null)
			throw new IllegalArgumentException("Work Log Entries are required");
		if(workLog.getLogEntries().size()==0)
			throw new IllegalArgumentException("WorkLog Entries are empty.");
		
		Date date = workLog.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		User user = map(workLog.getUserDetails());
		
		List<Work> works = new ArrayList<>(workLog.getLogEntries().size());
		for(LogEntry entry: workLog.getLogEntries()) {
			Work work = new Work(user , date,entry.getJiraId() , entry.getDescription(), new WorkType(entry.getWorkType(),entry.getWorkType()), entry.getHour(), week);
			works.add(work);
		}
		
		return works;
		
	}

	public static User map(UserDetails ud) {
		User u = new User();
		u.setName(ud.getName());
		u.setId(ud.getId());
		u.setActive(ud.getActive());
		return u;
	}
	
	public static WorkLog map(Work work, WorkLog wl) {
		if(wl == null)
			wl = new WorkLog();
		wl.setDate(work.getDate());
		User u = work.getUser();
		if(u!=null) {
			UserDetails ud = new UserDetails();
			ud.setId(work.getUser().getId());
			ud.setName(work.getUser().getName());
			wl.setUserDetails(ud);
		}
		List<LogEntry> les =  wl.getLogEntries();
		if(les == null) {
			les = new ArrayList<>();
			wl.setLogEntries(les);
		}
		LogEntry entry = new LogEntry();
		entry.setDescription(work.getDescription());
		entry.setHour(work.getHours());
		entry.setJiraId(work.getJiraId());
		entry.setWorkType(work.getWorkType().getType());
		les.add(entry);
		return wl;
		
	}

	public static WorkLog map(List<Work> works) {
		if(works == null || works.size()==0)
			throw new IllegalArgumentException("Work Logs are required");
		
		WorkLog wl= new WorkLog();
		for(Work w : works) {
			wl = map(w,wl);
		}
		return wl;
	}

	public static UserDetails map(User user) {
		UserDetails ud = new UserDetails();
		ud.setActive(user.getActive());
		ud.setName(user.getName());
		ud.setEmail(user.getEmail());
		return ud;
	}

}

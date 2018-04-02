package com.mubasher.timesheet.controller.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkLog {
	
	private Date date;
	private UserDetails user;
	private List<LogEntry> logEntries;
	public List<LogEntry> getLogEntries() {
		return logEntries;
	}
	public void setLogEntries(List<LogEntry> logEntries) {
		this.logEntries = logEntries;
	}
	public UserDetails getUserDetails() {
		return user;
	}
	public void setUserDetails(UserDetails user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}

package com.mubasher.timesheet.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogEntry {
	
	private String jiraId;
	private String workType;
	private String description;
	private double hour;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getJiraId() {
		return jiraId;
	}
	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}
	public double getHour() {
		return hour;
	}
	public void setHour(double hour) {
		this.hour = hour;
	}
	
	@Override
	public String toString() {
		return "LogEntry [jiraId=" + jiraId + ", workType=" + workType + ", description=" + description + ", hour="
				+ hour + "]";
	}
	
	

}

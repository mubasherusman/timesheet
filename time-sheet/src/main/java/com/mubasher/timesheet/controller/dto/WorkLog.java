package com.mubasher.timesheet.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mubasher.timesheet.model.WorkType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkLog {
	
	@NotNull(message="* Please Select Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date currentDate;
	private String userEmail;
	@NotEmpty(message="* Please Select Jira Id")
	private String jiraId;
	@NotNull(message="* Please Select Work Type")
	private WorkType workType;
	private String description;
	private double hour;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public WorkType getWorkType() {
		return workType;
	}
	public void setWorkType(WorkType workType) {
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
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date date) {
		this.currentDate = date;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}

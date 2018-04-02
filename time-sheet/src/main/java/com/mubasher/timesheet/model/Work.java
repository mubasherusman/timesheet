package com.mubasher.timesheet.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = Work.TABLE_NAME)
public class Work{
	
	public static final String TABLE_NAME = "work_logs";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="JIRA_ID")
	private String jiraId;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private WorkType workType;
	
	private double hours;
	
	private int week;
	
	public Work() {
		super();
	}
	
	public Work(int id, Date date, String jiraId, String description, WorkType workType,
			double hours, int week) {
		this.id = id;
		this.date = date;
		this.jiraId=jiraId;
		this.description = description;
		this.workType = workType;
		this.hours = hours;
		this.week = week;
	}
	
	public Work(User user, Date date, String jiraId, String description, WorkType workType, double hour,
			int week) {
		this.user = user;
		this.date = date;
		this.jiraId=jiraId;
		this.description = description;
		this.workType = workType;
		this.hours = hour;
		this.week = week;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getJiraId() {
		return jiraId;
	}
	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}
	
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
	
	public double getHours() {
		return hours;
	}
	public void setHour(double hours) {
		this.hours = hours;
	}
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}

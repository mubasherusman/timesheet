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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Work.TABLE_NAME)
public class Work{
	
	public static final String TABLE_NAME = "work_logs";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="JIRA_ID")
	private String jiraId;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private WorkType workType;
	
	private double hours;
	
	private int week;
}

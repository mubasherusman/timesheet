package com.mubasher.timesheet.controller.dto.response;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;

public class ApplicationDetails extends BaseResponce {

	private String version;
	private String applicationName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	
	public ApplicationDetails() {
		super();
		this.timestamp = new Date();
	}
	
	public ApplicationDetails(ApiCode code) {
		this(code,null,null);
	}

	public ApplicationDetails(ApiCode code, String version, String applicationName) {
		super(code);
		this.version = version;
		this.applicationName = applicationName;
		this.timestamp = new Date();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
}

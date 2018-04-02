package com.mubasher.timesheet.controller.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponce {
	
	private String status;
	private String message;
	
	public BaseResponce() {
	}
	
	public BaseResponce(ApiCode code) {
		setStatus(code.getStatusCode());
		setMessage(code.getStatusMessage());
	}
	
	public void setApiStatus(ApiCode code) {
		setStatus(code.getStatusCode());
		setMessage(code.getStatusMessage());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

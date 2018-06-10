package com.mubasher.timesheet.controller.dto.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;

public class ApiErrorResponse extends BaseResponce {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private String debugMessage;
    private List<ApiSubError> subErrors;
    
    private ApiErrorResponse() {
        timestamp = new Date();
    }
    
    public ApiErrorResponse(String status) {
    	this(status,"Unexpected error");
    }
    
    public ApiErrorResponse(ApiCode status) {
    	this(status.getStatusCode(),status.getStatusMessage());
    }
    
    public ApiErrorResponse(String status, Throwable ex) {
        this(status,"Unexpected error",ex);
    }
    
    public ApiErrorResponse(String status, String message ) {
    	this(status,message,null);
    }
    
    public ApiErrorResponse(int status, String message ) {
    	this(String.valueOf(status),message,null);
    }
    
    public ApiErrorResponse(String status, String message, Throwable ex) {
        this();
        super.setStatus(status);
        super.setMessage(message);
        this.debugMessage = ex==null? null:ex.getLocalizedMessage();
    }

    public void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }
	
	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}
	
	@Override
	public String toString() {
		return "ApiErrorResponse [message=" + super.getMessage() + ", timestamp=" + timestamp + ", debugMessage=" + debugMessage
				+ ", subErrors=" + (subErrors==null?"":subErrors) + "]";
	}
	
	
}

package com.mubasher.timesheet.controller.exceptions;

import com.mubasher.timesheet.controller.dto.codes.ApiCode;

public class ApiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5385087689286392916L;
	private ApiCode apiCode;
	@SuppressWarnings("unused")
	private String message;

    public ApiException(ApiCode apiCode, String message) {
        super(message);
        this.apiCode = apiCode;
    }
    
    public ApiException(ApiCode apiCode, Throwable th) {
        super(th);
        this.apiCode = apiCode;
    }

	public ApiCode getApiCode() {
		return apiCode;
	}

}

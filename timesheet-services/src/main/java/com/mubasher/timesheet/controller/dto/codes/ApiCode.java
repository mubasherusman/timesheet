package com.mubasher.timesheet.controller.dto.codes;

public enum ApiCode {
	SUCCESS("000","Success"), 
	
	ERROR("100","Error In WorkLogControllerTest."),
	
	FORM_SUBMITTED("300","Loan Application Form Submitted Successfully."),
	FORM_PENDING("301","Loan Application Form Pending for approval."),
	FORM_APPROVED("302","Loan Application Form Approved."),
	FORM_REJECTED("303","Loan Application Form Rejected."),
	FORM_INCOMPLETE("304","Loan Application Form is incomplete due to Form Errors."),
	FORM_NOT_FOUND("305","Loan Application Form not found. Check Cyour customer id"),
	
	VALIDATION_ERROR("400","Error In Form Data.");
	
	private String statusCode;
	private String statusMessage;
	
	ApiCode(String code, String message) {
		this.statusCode=code;
		this.statusMessage = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public ApiCode findStatus(String apiCodes) {
		for(ApiCode aCodes : values()) {
			if(aCodes.statusCode.equals(apiCodes))
				return aCodes;
		}
		return null;
	}
}

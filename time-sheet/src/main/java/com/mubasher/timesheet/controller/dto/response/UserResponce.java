package com.mubasher.timesheet.controller.dto.response;

import com.mubasher.timesheet.controller.dto.UserDetails;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;

public class UserResponce extends BaseResponce {
	
	private UserDetails userDetails;
	
	public UserResponce() {
		super();
	}
	
	public UserResponce(ApiCode status) {
        super.setStatus(status.getStatusCode());
        super.setMessage(status.getStatusMessage());
    }
	
	public UserResponce(ApiCode status,UserDetails user) {
        super.setStatus(status.getStatusCode());
        super.setMessage(status.getStatusMessage());
        setUserDetails(user);
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}

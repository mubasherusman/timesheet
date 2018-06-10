package com.mubasher.timesheet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mubasher.timesheet.controller.dto.UserDetails;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;
import com.mubasher.timesheet.controller.dto.response.BaseResponce;
import com.mubasher.timesheet.controller.dto.response.UserResponce;
import com.mubasher.timesheet.controller.exceptions.ApiException;
import com.mubasher.timesheet.model.User;
import com.mubasher.timesheet.service.UserService;
import com.mubasher.timesheet.utils.BeanMapper;


//@RestController
@RequestMapping(APIConstants.API_URL_PREFIX_USER)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = APIConstants.API_URL_PREFIX_REGISTER, method = RequestMethod.POST)
	public @ResponseBody BaseResponce registerUser(@Valid UserDetails ud) throws ApiException {
		User userExists = userService.findUserByEmail(ud.getEmail());
		if (userExists != null) {
			throw new ApiException(ApiCode.ERROR, "There is already a user registered with the email provided");
		} else {
			User u = userService.saveUser(BeanMapper.map(ud));
			return new UserResponce(ApiCode.SUCCESS, BeanMapper.map(u));
		}
	}

}

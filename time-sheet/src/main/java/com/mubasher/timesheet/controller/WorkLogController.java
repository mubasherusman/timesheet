package com.mubasher.timesheet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mubasher.timesheet.controller.dto.WorkLog;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;
import com.mubasher.timesheet.controller.dto.response.BaseResponce;
import com.mubasher.timesheet.controller.dto.response.WorkLogResponse;
import com.mubasher.timesheet.controller.exceptions.ApiException;
import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.service.WorkLogService;
import com.mubasher.timesheet.utils.BeanMapper;


//@RestController
@RequestMapping(APIConstants.API_URL_PREFIX_WORK_LOGS)
public class WorkLogController {
	
	@Autowired
	private WorkLogService workLogService;
	
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody BaseResponce saveWork(@Valid @RequestBody WorkLog workLog) throws ApiException {
		
		if(workLog == null)
			throw new ApiException(ApiCode.ERROR, "Null Form Data Received");
		
		Work works = BeanMapper.map(workLog);
		
		Work count = workLogService.logWork(works);
		
		return new WorkLogResponse(ApiCode.SUCCESS, 1) ;
		
	}
	
	@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody BaseResponce getWorkLogs(@RequestParam(defaultValue="1") int userId) throws ApiException {
		List<Work> works = workLogService.findWorksByUserId(userId);
		List<WorkLog> workLog = BeanMapper.mapWork(works);
		return new WorkLogResponse(ApiCode.SUCCESS, workLog) ;
		
	}

}

package com.mubasher.timesheet.controller.dto.response;

import java.util.List;

import com.mubasher.timesheet.controller.dto.WorkLog;
import com.mubasher.timesheet.controller.dto.codes.ApiCode;

public class WorkLogResponse extends BaseResponce {
	
	private int saveCount;
	private List<WorkLog> workLog;
	
	public WorkLogResponse() {
		super();
	}
	
	public WorkLogResponse(ApiCode status) {
        super.setStatus(status.getStatusCode());
        super.setMessage(status.getStatusMessage());
    }
	
	public WorkLogResponse(ApiCode status,int count) {
        super.setStatus(status.getStatusCode());
        super.setMessage(status.getStatusMessage());
        saveCount = count;
	}
	
	public WorkLogResponse(ApiCode status,List<WorkLog> workLogs) {
        super.setStatus(status.getStatusCode());
        super.setMessage(status.getStatusMessage());
        this.workLog = workLogs;
	}

	public int getSaveCount() {
		return saveCount;
	}

	public void setSaveCount(int saveCount) {
		this.saveCount = saveCount;
	}

	public List<WorkLog> getWorkLog() {
		return workLog;
	}

	public void setWorkLog(List<WorkLog> workLogs) {
		this.workLog = workLogs;
	}

	@Override
	public String toString() {
		return "WorkLogResponse [saveCount=" + saveCount + ", workLog=" + workLog + "]";
	}
	
	
}

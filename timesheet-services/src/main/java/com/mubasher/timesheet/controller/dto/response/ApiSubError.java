package com.mubasher.timesheet.controller.dto.response;

public class ApiSubError  {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiSubError() {
		super();
	}

	public ApiSubError(String object, String message) {
        this.setObject(object);
        this.setMessage(message);
    }

	public ApiSubError(String object, String field, Object rejectedValue, String message) {
		this.setObject(object);
        this.setMessage(message);
        this.setRejectedValue(rejectedValue);
        this.setField(field);
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "\nApiSubError [object=" + object + ", field=" + field + ", rejectedValue=" + rejectedValue + ", message="
				+ message + "]";
	}
	
	
}

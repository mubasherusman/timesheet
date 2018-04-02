package com.mubasher.timesheet.controller.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mubasher.timesheet.controller.dto.response.ApiErrorResponse;
import com.mubasher.timesheet.controller.dto.response.ApiSubError;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiErrorResponse(BAD_REQUEST.value()+"", error, ex),BAD_REQUEST);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()+"", builder.substring(0, builder.length() - 2), ex),HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,  
    		WebRequest request) {    	
    	// default case
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(BAD_REQUEST.value()+"");
        apiErrorResponse.setMessage("Validation error");
        addValidationErrors(apiErrorResponse,ex.getBindingResult().getFieldErrors());
        addValidationError(apiErrorResponse,ex.getBindingResult().getGlobalErrors());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(BAD_REQUEST.value()+"");
        apiErrorResponse.setMessage("Validation error");
        addValidationErrors(apiErrorResponse,ex.getConstraintViolations());
        return buildResponseEntity(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       /* ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        LogEntry.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());*/
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value()+"", error, ex),BAD_REQUEST);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()+"", error, ex),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles ApiException. Created to encapsulate errors with more detail.
     *
     * @param ex the ApiException
     * @return the ApiError object
     */
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(
            ApiException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getApiCode());
        apiErrorResponse.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiErrorResponse,HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Exception, handle generic MethodArgumentTypeMismatchException.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(BAD_REQUEST.value()+"");
        apiErrorResponse.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiErrorResponse.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiErrorResponse,BAD_REQUEST);
    }

    /**
     * Utility Methods
     */
    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse,HttpStatus status) {
        return new ResponseEntity<>(apiErrorResponse,status);
    }
    
    private void addValidationErrors(ApiErrorResponse apiErrorResponse,List<FieldError> fieldErrors) {
        for(FieldError obj: fieldErrors) {
        	addValidationError(apiErrorResponse,obj);
        }
    }
    
    private void addValidationError(ApiErrorResponse apiErrorResponse,List<ObjectError> globalErrors) {
        for(ObjectError obj: globalErrors) {
        	addValidationError(apiErrorResponse,obj);
        }
    }
    
    private void addValidationErrors(ApiErrorResponse apiErrorResponse,Set<ConstraintViolation<?>> constraintViolations) {
        for(ConstraintViolation<?> obj:constraintViolations) {
        	addValidationError(apiErrorResponse,obj);
        }
    }
    
    private void addValidationError(ApiErrorResponse apiErrorResponse,ObjectError objectError) {
        this.addValidationError(apiErrorResponse,
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }
    
    private void addValidationError(ApiErrorResponse apiErrorResponse,FieldError fieldError) {
        this.addValidationError(apiErrorResponse,
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }
    
    private void addValidationError(ApiErrorResponse apiErrorResponse,String object, String field, Object rejectedValue, String message) {
    	apiErrorResponse.addSubError(new ApiSubError(object, field, rejectedValue, message));
    }

    private void addValidationError(ApiErrorResponse apiErrorResponse,String object, String message) {
    	apiErrorResponse.addSubError(new ApiSubError(object, message));
    }
    
    private void addValidationError(ApiErrorResponse apiErrorResponse,ConstraintViolation<?> cv) {
        addValidationError(apiErrorResponse,
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }
}

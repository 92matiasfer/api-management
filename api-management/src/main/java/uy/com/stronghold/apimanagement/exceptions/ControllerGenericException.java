package uy.com.stronghold.apimanagement.exceptions;

import java.util.Date;

//import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ControllerGenericException {
	
//	private static final Logger logger = Logger.getLogger(ControllerGenericException.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericExcepction(Exception e, WebRequest request){
		ServletWebRequest r = (ServletWebRequest)request;
		HttpMethod httpMethod = r.getHttpMethod();
	    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), 
	    		e.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
//	    logger.info(error.toString());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package uy.com.stronghold.apimanagement.exceptions;

import java.util.Date;

//import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
@Order(1)
public class ValidationExceptionController {

//	private static final Logger logger = Logger.getLogger(ValidationExceptionController.class);
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handlerValidationException(ValidationException e, WebRequest request){
		ServletWebRequest swr = (ServletWebRequest) request;
		HttpStatus httpStatus = e.geterror().getHttpStatus();
		HttpMethod httpMethod = swr.getHttpMethod();
		ErrorResponse error = new ErrorResponse(new Date(), httpStatus.value(), httpStatus.name(), 
	    		e.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
//		logger.info(error.toString());
		return new ResponseEntity<Object>(error, httpStatus);
	}
}

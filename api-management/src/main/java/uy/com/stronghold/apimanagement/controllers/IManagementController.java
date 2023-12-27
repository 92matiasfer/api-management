package uy.com.stronghold.apimanagement.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;

@RestController
public interface IManagementController {

	@GetMapping(value="/management/systemStartup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSystemStartup( 
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	
	
}

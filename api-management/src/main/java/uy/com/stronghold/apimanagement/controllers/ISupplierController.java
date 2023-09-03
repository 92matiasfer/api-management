package uy.com.stronghold.apimanagement.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Supplier;

@RestController
public interface ISupplierController {

	@GetMapping(value="/suppliers/{id}")
	public ResponseEntity<Object> getSupplier(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@GetMapping(value="/suppliers")
	public ResponseEntity<Object> getSuppliers(@RequestParam("name") Optional<String> nameParam, 
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PostMapping(value="/suppliers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSupplier(@RequestBody Supplier supplier,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PutMapping(value="/suppliers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSupplier(@RequestBody Supplier supplier,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@DeleteMapping(value="/suppliers/{id}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
}

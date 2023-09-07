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
import uy.com.stronghold.apimanagement.models.Box;



@RestController
public interface IBoxController {

	@GetMapping(value="/boxes/{id}")
	public ResponseEntity<Object> getBox(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;

	@GetMapping(value="/boxes")
	public ResponseEntity<Object> getBoxes(@RequestParam("name") Optional<String> nameParam, 
			@RequestParam("idBuilding") Optional<String> idBuildingParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PostMapping(value="/boxes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveBox(@RequestBody Box box,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;

	@PutMapping(value="/boxes")
	public ResponseEntity<Object> updateBox(@RequestBody Box box,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;

	@DeleteMapping(value="/boxes/{id}")
	public ResponseEntity<Object> deleteBox(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;





}

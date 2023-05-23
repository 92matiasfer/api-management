package uy.com.stronghold.apimanagement.controllers;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;


@RestController
public interface IBuildingController {

	@GetMapping(value={"/buildings", "/buildings/{id}"})
	public ResponseEntity<Object> getBuildings(@PathVariable("id") Optional<String> idParam,
			@RequestParam("name") Optional<String> nameParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PostMapping(value="/buildings")
	public ResponseEntity<Object> saveBuilding(Building building,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PutMapping(value="/buildings")
	public ResponseEntity<Object> updateBuilding(Building building,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@DeleteMapping(value="/buildings/{id}")
	public ResponseEntity<Object> deleteBuilding(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
}

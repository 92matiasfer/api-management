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
import uy.com.stronghold.apimanagement.models.SettlementMonth;

@RestController
public interface ISettlementMonthController {

	@GetMapping(value="/settlementMonths/{id}")
	public ResponseEntity<Object> getSettlementMonth(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@GetMapping(value="/settlementMonths")
	public ResponseEntity<Object> getSettlementMonths(@RequestParam("year") Optional<String> yearParam,
			@RequestParam("month") Optional<String> monthParam, @RequestParam("idBuilding") Optional<String> idBuildingParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PostMapping(value="/settlementMonths", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSettlementMonths(@RequestBody SettlementMonth settlementMonth,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PutMapping(value="/settlementMonths", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSettlementMonths(@RequestBody SettlementMonth settlementMonth,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@DeleteMapping(value="/settlementMonths/{id}")
	public ResponseEntity<Object> deleteSettlementMonths(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
}

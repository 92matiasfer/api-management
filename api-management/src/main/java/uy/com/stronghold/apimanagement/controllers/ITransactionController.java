package uy.com.stronghold.apimanagement.controllers;

import java.text.ParseException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Transaction;
import uy.com.stronghold.apimanagement.models.UnitTransaction;

@RestController
public interface ITransactionController {

	@GetMapping(value="/transactions/{id}")
	public ResponseEntity<Object> getTransaction(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@GetMapping(value="/transactions")
	public ResponseEntity<Object> getTransactions(@RequestParam("idBuilding") Optional<String> idBuildingParam, 
			@RequestParam("dateFrom") Optional<String> dateFromParam, @RequestParam("dateTo") Optional<String> dateToParam,
			@RequestParam("type") Optional<String> typeParam, 
			@RequestParam("idMonth") Optional<String> idSettlementMonthParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PostMapping(value="/transactions/units", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUnitTransaction(@RequestBody UnitTransaction transaction,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;

	
	@PostMapping(value="/transactions/suppliers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSupplierTransaction(@RequestBody SupplierTransaction transaction,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PutMapping(value="/transactions/units", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUnitTransaction(@RequestBody UnitTransaction transaction,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@PutMapping(value="/transactions/suppliers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSupplierTransaction(@RequestBody SupplierTransaction transaction,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	
	@DeleteMapping(value="/transactions/{id}")
	public ResponseEntity<Object> deleteTransaction(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
}

package uy.com.stronghold.apimanagement.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;

@RestController
public interface IBoxSettlementMonthController {

    @GetMapping(value="/boxesSettlementMonths/{id}")
    public ResponseEntity<Object> getBoxSettlementMonth(@PathVariable("id") Optional<String> idParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;


    @GetMapping(value="/boxesSettlementMonths")
	public ResponseEntity<Object> getBoxesSettlementMonths(@RequestParam("settlementMonth") Optional<String> settlementMonthParam,
			@RequestParam("box") Optional<String> boxParam, @RequestParam("unit") Optional<String> unitParam,
			@RequestHeader HttpHeaders headers, HttpServletRequest request) throws ValidationException;
	    
}

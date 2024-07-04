package uy.com.stronghold.apimanagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.BoxSettlementMonth;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class BoxSettlementMonthController implements IBoxSettlementMonthController {

    @Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;

    @Override
    public ResponseEntity<Object> getBoxSettlementMonth(Optional<String> idParam, HttpHeaders headers,
            HttpServletRequest request) throws ValidationException {
        
        throw new UnsupportedOperationException("Unimplemented method 'getBoxSettlementMonth'");
    }

    @Override
    public ResponseEntity<Object> getBoxesSettlementMonths(Optional<String> settlementMonthParam, Optional<String> boxParam,
            Optional<String> unitParam, HttpHeaders headers, HttpServletRequest request) throws ValidationException {
        
        int settlementMonth = validation.getInteger(settlementMonthParam);
		int box = validation.getInteger(boxParam);
		int unit = validation.getInteger(unitParam);
		List<BoxSettlementMonth> boxesSettlementMonth = apiManagementServiceImp.getBoxesSettlementMonths(settlementMonth, box, unit);
		return new ResponseEntity<Object>(boxesSettlementMonth, HttpStatus.OK);                
        
    }

    



}

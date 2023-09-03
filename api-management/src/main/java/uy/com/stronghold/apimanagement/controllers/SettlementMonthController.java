package uy.com.stronghold.apimanagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class SettlementMonthController implements ISettlementMonthController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getSettlementMonth(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {

		int id = validation.getInteger(idParam);
		SettlementMonth settlementMonth = apiManagementServiceImp.getSettlementMonth(id);
		return new ResponseEntity<Object>(settlementMonth, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getSettlementMonths(Optional<String> yearParam, Optional<String> monthParam,
			Optional<String> idBuildingParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		int year = validation.getInteger(yearParam);
		int month = validation.getInteger(monthParam);
		int idBuilding = validation.getInteger(idBuildingParam);
		List<SettlementMonth> SettlementMonths = apiManagementServiceImp.getSettlementMonths(year, month, idBuilding);
		return new ResponseEntity<Object>(SettlementMonths, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveSettlementMonths(SettlementMonth settlementMonth, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		validation.validateSaveSettlementMonths(settlementMonth);
		apiManagementServiceImp.saveSettlementMonths(settlementMonth);
		return new ResponseEntity<Object>("El Mes Liquidacion ha sido creado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateSettlementMonths(SettlementMonth settlementMonth, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
	
		validation.validateUpdateSettlementMonths(settlementMonth);
		if(apiManagementServiceImp.getSettlementMonth(settlementMonth.getId()) == null) 
			throw new ValidationException(Errores.SETTLEMENT_MONTH_NOT_FOUND);
		apiManagementServiceImp.updateSettlementMonths(settlementMonth);
		return new ResponseEntity<Object>("El Mes Liquidacion ha sido modificado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteSettlementMonths(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		int id = validation.getInteger(idParam);
		SettlementMonth settlementMonth = apiManagementServiceImp.getSettlementMonth(id);
		if(settlementMonth == null) throw new ValidationException(Errores.SETTLEMENT_MONTH_NOT_FOUND);
		apiManagementServiceImp.deleteSettlementMonths(settlementMonth);
		return new ResponseEntity<Object>("El Mes Liquidacion ha sido eliminado correctamente", HttpStatus.OK);
	}

}

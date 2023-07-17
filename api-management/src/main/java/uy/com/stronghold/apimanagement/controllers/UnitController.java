package uy.com.stronghold.apimanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class UnitController implements IUnitController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getUnit(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		
		Unit unit = apiManagementServiceImp.getUnit(id);
		
		return new ResponseEntity<Object>(unit, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getUnits(Optional<String> numberParam, Optional<String> idBuildingParam,
			Optional<String> unitTypeParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		String number = validation.normalizeInput(numberParam);
		int idBuilding = validation.getInteger(idBuildingParam);
		UnitType unitType = validation.getUnitType(unitTypeParam);
		
		List<Unit> units = apiManagementServiceImp.getUnits(idBuilding, number, unitType);
		
		return new ResponseEntity<Object>(units, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveUnit(Unit unit, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> updateUnit(Unit unit, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteUnit(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}

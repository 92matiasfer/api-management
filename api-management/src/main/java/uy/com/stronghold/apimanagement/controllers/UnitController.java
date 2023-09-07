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
import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
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
		
		validation.validateSaveUnit(unit);
		
		apiManagementServiceImp.saveUnit(unit);
		
		return new ResponseEntity<Object>("La unidad ha sido creada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateUnit(Unit unit, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		validation.validateUpdateUnit(unit);
		
		if(apiManagementServiceImp.getUnit(unit.getId()) == null) throw new ValidationException(Errores.UNIT_NOT_FOUND);
		
		apiManagementServiceImp.saveUnit(unit);
		
		return new ResponseEntity<Object>("La unidad ha sido modificada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteUnit(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		//Obtengo al edificio solicitado
		Unit unit = apiManagementServiceImp.getUnit(id);
		//Verifico que exista el edificio
		if(unit == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
				
		apiManagementServiceImp.deleteUnit(unit);
		
		return new ResponseEntity<Object>("La unidad ha sido eliminada correctamente", HttpStatus.OK);

	}

}

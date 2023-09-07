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
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class BuildingController implements IBuildingController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	

	@Override
	public ResponseEntity<Object> getBuilding(Optional<String> idParam, 
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		int id = validation.getInteger(idParam);
		Building building = apiManagementServiceImp.getBuilding(id);
		return new ResponseEntity<Object>(building, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Object> getBuildings(Optional<String> nameParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		String name = validation.normalizeInput(nameParam);
		List<Building> buildings = apiManagementServiceImp.getBuildings(name);
		return new ResponseEntity<Object>(buildings, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveBuilding(Building building, HttpHeaders headers, 
			HttpServletRequest request) throws ValidationException {
		
		validation.validateSaveBuilding(building);
		apiManagementServiceImp.saveBuilding(building);
		return new ResponseEntity<Object>("El Edificio ha sido creado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateBuilding(Building building, HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		validation.validateUpdateBuilding(building);
		if(apiManagementServiceImp.getBuilding(building.getId()) == null) 
			throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		apiManagementServiceImp.updateBuilding(building);
		return new ResponseEntity<Object>("El Edificio ha sido modificado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteBuilding(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {

		int id = validation.getInteger(idParam);
		Building building = apiManagementServiceImp.getBuilding(id);
		if(building == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		apiManagementServiceImp.deleteBuilding(building);
		return new ResponseEntity<Object>("El Edificio ha sido eliminado correctamente", HttpStatus.OK);
	}

}

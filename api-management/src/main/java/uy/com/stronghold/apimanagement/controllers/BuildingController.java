package uy.com.stronghold.apimanagement.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.repositories.BuildingRepository;
import uy.com.stronghold.apimanagement.resources.ApiManagementServiceImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class BuildingController implements IBuildingController {

	@Autowired
	ApiManagementServiceImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	

	@Override
	public ResponseEntity<Object> getBuilding(Optional<String> idParam, 
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		//Obtengo al edificio solicitado
		Building building = apiManagementServiceImp.getBuilding(id);
		
		return new ResponseEntity<Object>(building, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Object> getBuildings(Optional<String> nameParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		List<Building> buildings = new ArrayList<>();
		//Normalizo y valido la entrada de los datos
		String name = validation.normalizeInput(nameParam);
		//Obtengo la lista de edificios que coincida con los parámetros ingresados
		buildings = apiManagementServiceImp.getBuildings(name);

		return new ResponseEntity<Object>(buildings, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveBuilding(Building building, HttpHeaders headers, 
			HttpServletRequest request) throws ValidationException {
		
		//Valido la información ingresada por el usuario
		validation.validateSaveBuilding(building);
		
		apiManagementServiceImp.saveBuilding(building);
		
		return new ResponseEntity<Object>("El edificio ha sido creado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateBuilding(Building building, HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		//Valido la información ingresada por el usuario
		validation.validateUpdateBuilding(building);
		//Verifico que exista el edificio
		if(apiManagementServiceImp.getBuilding(building.getId()) == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		
		apiManagementServiceImp.saveBuilding(building);
		
		return new ResponseEntity<Object>("El edificio ha sido modificado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteBuilding(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		//Obtengo al edificio solicitado
		Building building = apiManagementServiceImp.getBuilding(id);
		//Verifico que exista el edificio
		if(building == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
				
		apiManagementServiceImp.deleteBuilding(building);
		
		return new ResponseEntity<Object>("El edificio ha sido eliminado correctamente", HttpStatus.OK);
	}

}

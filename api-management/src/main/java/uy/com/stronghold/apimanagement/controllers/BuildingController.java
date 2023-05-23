package uy.com.stronghold.apimanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
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
	public ResponseEntity<Object> getBuildings(Optional<String> idParam, Optional<String> nameParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		String name = validation.normalizeInput(nameParam);
		
		List<Building> buildings = apiManagementServiceImp.getBuildings(id, name);
		
		return new ResponseEntity<Object>(buildings, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> saveBuilding(Building building, HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> updateBuilding(Building building, HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteBuilding(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}

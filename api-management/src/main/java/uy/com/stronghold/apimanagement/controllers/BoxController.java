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
import uy.com.stronghold.apimanagement.models.Box;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class BoxController implements IBoxController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getBox(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {

		int id = validation.getInteger(idParam);
		Box box = apiManagementServiceImp.getBox(id); 
		return new ResponseEntity<Object>(box, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getBoxes(Optional<String> nameParam, Optional<String> idBuildingParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		String name = validation.normalizeInput(nameParam);
		int idBuilding = validation.getInteger(idBuildingParam);
		List<Box> boxes = apiManagementServiceImp.getBoxes(name, idBuilding);
		if(boxes == null) throw new ValidationException(Errores.BOX_NOT_FOUND);
		return new ResponseEntity<Object>(boxes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveBox(Box box, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {

		validation.validateSaveBox(box);
		apiManagementServiceImp.saveBox(box);
		return new ResponseEntity<Object>("La Caja ha sido creada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateBox(Box box, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		validation.validateUpdateBox(box);
		if(apiManagementServiceImp.getBox(box.getId()) == null)
			throw new ValidationException(Errores.BOX_NOT_FOUND);
		apiManagementServiceImp.saveBox(box);
		return new ResponseEntity<Object>("La Caja ha sido modificada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteBox(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		int id = validation.getInteger(idParam);
		Box box = apiManagementServiceImp.getBox(id);
		if(box == null) throw new ValidationException(Errores.BOX_NOT_FOUND);
		apiManagementServiceImp.deleteBox(box);
		return new ResponseEntity<Object>("La Caja ha sido eliminada correctamente", HttpStatus.OK);
	}

}

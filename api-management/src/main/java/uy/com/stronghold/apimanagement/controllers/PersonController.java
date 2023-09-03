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
import uy.com.stronghold.apimanagement.models.Person;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class PersonController implements IPersonController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getPerson(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		int id = validation.getInteger(idParam);
		Person person = apiManagementServiceImp.getPerson(id);
		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getPersons(Optional<String> nameParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		String name = validation.normalizeInput(nameParam);
		List<Person> persons = apiManagementServiceImp.getPersons(name);
		return new ResponseEntity<Object>(persons, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> savePerson(Person person, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		validation.validateSavePerson(person);
		apiManagementServiceImp.savePerson(person);
		return new ResponseEntity<Object>("La Persona ha sido creada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updatePerson(Person person, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		validation.validateUpdatePerson(person);
		if(apiManagementServiceImp.getPerson(person.getId()) == null) 
			throw new ValidationException(Errores.PERSON_NOT_FOUND);
		apiManagementServiceImp.updatePerson(person);
		return new ResponseEntity<Object>("La Persona ha sido modificada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deletePerson(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
	
		int id = validation.getInteger(idParam);
		Person person = apiManagementServiceImp.getPerson(id);
		if(person == null) throw new ValidationException(Errores.PERSON_NOT_FOUND);
		apiManagementServiceImp.deletePerson(person);
		return new ResponseEntity<Object>("La Persona ha sido eliminada correctamente", HttpStatus.OK);
	}

	
}

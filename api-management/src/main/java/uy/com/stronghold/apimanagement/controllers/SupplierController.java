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
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class SupplierController implements ISupplierController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getSupplier(Optional<String> idParam, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		int id = validation.getInteger(idParam);
		Supplier supplier = apiManagementServiceImp.getSupplier(id);
		return new ResponseEntity<Object>(supplier, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getSuppliers(Optional<String> nameParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		String name = validation.normalizeInput(nameParam);
		List<Supplier> suppliers = apiManagementServiceImp.getSuppliers(name);
		return new ResponseEntity<Object>(suppliers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveSupplier(Supplier supplier, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		validation.validateSaveSupplier(supplier);
		apiManagementServiceImp.saveSupplier(supplier);
		return new ResponseEntity<Object>("El Proveedor ha sido creado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateSupplier(Supplier supplier, HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {

		validation.validateUpdateSupplier(supplier);
		if(apiManagementServiceImp.getBuilding(supplier.getId()) == null) 
			throw new ValidationException(Errores.SUPPLIER_NOT_FOUND);
		apiManagementServiceImp.updateSupplier(supplier);
		return new ResponseEntity<Object>("El Proveedor ha sido modificado correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteSupplier(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {

		int id = validation.getInteger(idParam);
		Supplier supplier = apiManagementServiceImp.getSupplier(id);
		if(supplier == null) throw new ValidationException(Errores.SUPPLIER_NOT_FOUND);
		apiManagementServiceImp.deleteSupplier(supplier);
		return new ResponseEntity<Object>("El Proveedor ha sido eliminado correctamente", HttpStatus.OK);
	}

}

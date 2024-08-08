package uy.com.stronghold.apimanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import io.micrometer.core.instrument.util.StringUtils;
import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.resources.IApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

@Controller
public class SupplierController implements ISupplierController {

	@Autowired
	IApiManagementImp apiManagementServiceImp;
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
		
		if(supplier != null &&  supplier.getBuildings() != null 
			&& !supplier.getBuildings().isEmpty() && !StringUtils.isBlank(supplier.getName())) {
			
			if(apiManagementServiceImp.getSuppliers(supplier.getName()) != null 
				&& !apiManagementServiceImp.getSuppliers(supplier.getName()).isEmpty()) 
				throw new ValidationException(Errores.SUPPLIER_ALREADY_EXISTS);

			//Normalizo la entrada de datos
			supplier.setName(validation.normalizeInput(supplier.getName()));
			supplier.setDescription(validation.normalizeInput(supplier.getDescription()));
			supplier.setAddress(validation.normalizeInput(supplier.getAddress()));
			supplier.setEmail(validation.normalizeInput(supplier.getEmail()));
			supplier.setObservation(validation.normalizeInput(supplier.getObservation()));

			// Guardar el proveedor
			Supplier savedSupplier = apiManagementServiceImp.saveSupplier(supplier);
			if (savedSupplier == null) {
				throw new ValidationException(Errores.ERROR_SAVE_SUPPLIER);
			}

			Map<String, Object> response = new HashMap<>();
        	response.put("message", "El proveedor ha sido registrado correctamente");
        	response.put("supplier", savedSupplier);
			response.put("status", HttpStatus.OK.value());

        	return new ResponseEntity<>(response, HttpStatus.OK);	

		} else throw new ValidationException(Errores.CAMPOS_NULL);
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

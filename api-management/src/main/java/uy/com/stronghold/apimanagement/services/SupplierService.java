package uy.com.stronghold.apimanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.repositories.ISupplierRepository;

@Service
public class SupplierService implements ISupplierService {

	@Autowired
	private ISupplierRepository repository;
	
	
	@Override
	public Supplier getSupplier(int id) throws ValidationException {
		Supplier supplier = repository.getSupplier(id);
		if(supplier == null) throw new ValidationException(Errores.SUPPLIER_NOT_FOUND);
		return supplier;
	}

	@Override
	public List<Supplier> getSuppliers(String name) throws ValidationException {
		List<Supplier> suppliers = new ArrayList<>();
		suppliers = repository.getSuppliers(name);
//		if(suppliers == null || suppliers.isEmpty()) throw new ValidationException(Errores.SUPPLIER_NOT_FOUND);
		return suppliers;
	}

	@Override
	public Supplier saveSupplier(Supplier supplier) throws ValidationException {
		return repository.save(supplier);
	}
	
	@Override
	public void updateSupplier(Supplier supplier) throws ValidationException {
		if(repository.save(supplier) == null) throw new ValidationException(Errores.ERROR_UPDATE_SUPPLIER);
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		repository.delete(supplier);
	}

}

package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Supplier;

@Service
public interface ISupplierService {

	Supplier getSupplier(int id) throws ValidationException;

	List<Supplier> getSuppliers(String name) throws ValidationException;

	Supplier saveSupplier(Supplier supplier) throws ValidationException;

	void updateSupplier(Supplier supplier) throws ValidationException;
	
	void deleteSupplier(Supplier supplier) throws ValidationException;
}

package uy.com.stronghold.apimanagement.resources;

import java.util.List;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.Supplier;

public interface IApiManagementImp {

	/*
	 * Building
	 */

	public Building getBuilding(int id) throws ValidationException;
	
	public List<Building> getBuildings(String name) throws ValidationException;

	public Building saveBuilding(Building building) throws ValidationException;

	public void deleteBuilding(Building building) throws ValidationException;

	/*
	 * Supplier
	 */

	public Supplier getSupplier(int id) throws ValidationException;

    public List<Supplier> getSuppliers(String name) throws ValidationException;

    public Supplier saveSupplier(Supplier supplier) throws ValidationException;

    public void updateSupplier(Supplier supplier) throws ValidationException;

    public void deleteSupplier(Supplier supplier) throws ValidationException;


}

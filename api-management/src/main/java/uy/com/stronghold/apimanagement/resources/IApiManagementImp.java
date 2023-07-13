package uy.com.stronghold.apimanagement.resources;

import java.util.List;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;

public interface IApiManagementImp {

	public Building getBuilding(int id) throws ValidationException;
	
	public List<Building> getBuildings(String name) throws ValidationException;

	public void saveBuilding(Building building) throws ValidationException;

	public void deleteBuilding(Building building);
}

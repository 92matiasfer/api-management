package uy.com.stronghold.apimanagement.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.repositories.BuildingRepository;

@Service
public class ApiManagementServiceImp {
	
	@Autowired
	BuildingRepository buildingRepository;

	public Building getBuilding(int id) throws ValidationException {
		Building building = null;
		building = buildingRepository.getBuilding(id);
		if(building == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		return building;
	}
	
	public List<Building> getBuildings(String name) throws ValidationException {
		List<Building> buildings = null;
		buildings = buildingRepository.getBuildings(name);
		if(buildings == null || buildings.isEmpty()) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		return buildings;
	}

	public void saveBuilding(Building building) throws ValidationException {
		Building newBuilding = buildingRepository.saveBuilding(building);
		if(newBuilding == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
	}

	public void deleteBuilding(Building building) {
		buildingRepository.deleteBuilding(building);
	}

	
	
	
}

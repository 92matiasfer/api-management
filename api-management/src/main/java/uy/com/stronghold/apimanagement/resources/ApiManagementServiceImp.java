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

	public Building getBuilding(int id) {
		Building building = null;
		building = buildingRepository.getBuilding(id);
		return building;
	}
	
	public List<Building> getBuildings(String name) throws ValidationException {
		List<Building> buildings = null;
		try {
			buildings = buildingRepository.getBuildings(name);
			if(buildings == null || buildings.isEmpty()) {
				throw new ValidationException(Errores.BUILDING_NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		}
		return buildings;
	}

	
	
	
}

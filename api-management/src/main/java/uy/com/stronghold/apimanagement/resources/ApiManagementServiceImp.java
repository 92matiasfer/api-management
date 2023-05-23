package uy.com.stronghold.apimanagement.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.repositories.BuildingRepository;

@Service
public class ApiManagementServiceImp {
	
	@Autowired
	BuildingRepository buildingRepository;

	public List<Building> getBuildings(int id, String name) {
		return buildingRepository.getBuildings(id, name);

	}

	
	
	
}

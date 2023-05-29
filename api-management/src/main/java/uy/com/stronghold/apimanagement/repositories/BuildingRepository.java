package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.models.Building;

@Controller
public class BuildingRepository {

	@Autowired
	private IBuildingRepository buildingRepository;

	public Building getBuilding(int id) {
		return buildingRepository.getBuilding(id);
	}
	
	public List<Building> getBuildings(String name) {
		return buildingRepository.getBuildings(name);
	}

	public Building saveBuilding(Building building) {
		return buildingRepository.save(building);
	}

	public void deleteBuilding(Building building) {
		buildingRepository.delete(building);
	}
}

package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.models.Building;

@Controller
public class BuildingRepository {

	@Autowired
	private IBuildingRepository buildingRepository;

	public List<Building> getBuildings(int id, String name) {
		return buildingRepository.getBuildings(id, name);
	}
}

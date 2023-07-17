package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.repositories.BuildingRepository;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingRepository repository;
	
	@Override
	public Building getBuilding(int id) throws ValidationException {
		Building building = repository.getBuilding(id);
		if(building == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		return building;
	}

	@Override
	public List<Building> getBuildings(String name) throws ValidationException {
		List<Building> buildings = null;
		buildings = repository.getBuildings(name);
		if(buildings == null || buildings.isEmpty()) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		return buildings;
	}

	@Override
	public void saveBuilding(Building building) throws ValidationException {
		Building newBuilding =  repository.saveBuilding(building);
		if(newBuilding == null) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
	}

	@Override
	public void deleteBuilding(Building building) {
		repository.deleteBuilding(building);
	}

}

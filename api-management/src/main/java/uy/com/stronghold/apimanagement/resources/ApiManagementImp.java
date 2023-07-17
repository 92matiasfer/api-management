package uy.com.stronghold.apimanagement.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.repositories.BuildingRepository;
import uy.com.stronghold.apimanagement.services.IBuildingService;
import uy.com.stronghold.apimanagement.services.IUnitService;

@Service
public class ApiManagementImp implements IApiManagementImp {
	
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private IUnitService unitService;
	

	public Building getBuilding(int id) throws ValidationException {
		return buildingService.getBuilding(id);
	}
	
	public List<Building> getBuildings(String name) throws ValidationException {
		return buildingService.getBuildings(name);
	}

	public void saveBuilding(Building building) throws ValidationException {
		buildingService.saveBuilding(building);
	}

	public void deleteBuilding(Building building) {
		buildingService.deleteBuilding(building);
	}

	public Unit getUnit(int id) throws ValidationException {
		return unitService.getUnit(id);
	}

	public List<Unit> getUnits(int idBuilding, String number, UnitType unitType) throws ValidationException {
		return unitService.getUnits(idBuilding, number, unitType.toString());
	}

	
	
	
}

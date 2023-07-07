package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;

@Service
public interface IBuildingService {

	Building getBuilding(int id) throws ValidationException;

	List<Building> getBuildings(String name) throws ValidationException;

	void saveBuilding(Building building) throws ValidationException;

	void deleteBuilding(Building building);

	
}

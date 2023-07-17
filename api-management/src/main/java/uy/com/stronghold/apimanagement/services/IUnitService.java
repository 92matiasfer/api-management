package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Unit;

@Service
public interface IUnitService {

	Unit getUnit(int id) throws ValidationException;

	List<Unit> getUnits(int building, String number, String unitType) throws ValidationException;

	void saveUnit(Unit unit) throws ValidationException;

	void deleteUnit(Unit unit);
}

package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.repositories.UnitRepository;

@Service
public class UnitService implements IUnitService {

	@Autowired
	private UnitRepository unitRepository;
	
	@Override
	public Unit getUnit(int id) throws ValidationException {
		Unit unit = unitRepository.getUnit(id);
		if(unit == null) throw new ValidationException(Errores.UNIT_NOT_FOUND);
		return unit;
	}

	@Override
	public List<Unit> getUnits(int building, String number, String unitType) throws ValidationException {
		List<Unit> units = unitRepository.getUnits(building, number, unitType);
		if(units == null || units.isEmpty()) throw new ValidationException(Errores.UNIT_NOT_FOUND);
		return units;
	}

	@Override
	public void saveUnit(Unit unit) throws ValidationException {
		Unit newUnit = unitRepository.saveUnit(unit);
		if(newUnit == null) throw new ValidationException(Errores.UNIT_NOT_FOUND);
	}

	@Override
	public void deleteUnit(Unit unit) {
		unitRepository.deleteUnit(unit);
	}

}

package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.models.Unit;

@Controller
public class UnitRepository {

	@Autowired
	private IUnitRepository unitRepository;
	
	public Unit getUnit(int id) {
		return unitRepository.getUnit(id);
	}

	public List<Unit> getUnits(int building, String number, String unitType) {
		return unitRepository.getUnits(building, number, unitType);
	}

	public Unit saveUnit(Unit unit) {
		return unitRepository.save(unit);
	}

	public void deleteUnit(Unit unit) {
		unitRepository.delete(unit);
	}

	
}

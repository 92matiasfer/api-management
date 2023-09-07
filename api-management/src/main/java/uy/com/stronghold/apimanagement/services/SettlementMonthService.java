package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.repositories.ISettlementMonthRepository;

@Service
public class SettlementMonthService implements ISettlementMonthService {

	@Autowired
	private ISettlementMonthRepository repository;
	
	@Override
	public SettlementMonth getSettlementMonth(int id) throws ValidationException {
		SettlementMonth settlementMonth = repository.getSettlementMonth(id);
		if(settlementMonth == null) throw new ValidationException(Errores.SETTLEMENT_MONTH_NOT_FOUND);
		return settlementMonth;
	}

	@Override
	public List<SettlementMonth> getSettlementMonths(int year, int month, int idBuilding) throws ValidationException {
		List<SettlementMonth> settlementMonths = repository.getSettlementMonths(year, month, idBuilding);
		if(settlementMonths == null || settlementMonths.isEmpty()) throw new ValidationException(Errores.SETTLEMENT_MONTH_NOT_FOUND);
		return settlementMonths;
	}

	@Override
	public void saveSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		if(repository.save(settlementMonth) == null) throw new ValidationException(Errores.ERROR_SAVE_PERSON);

	}

	@Override
	public void updateSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		if(repository.save(settlementMonth) == null) throw new ValidationException(Errores.ERROR_SAVE_PERSON);

	}

	@Override
	public void deleteSettlementMonths(SettlementMonth settlementMonth) {
		repository.delete(settlementMonth);
	}

}

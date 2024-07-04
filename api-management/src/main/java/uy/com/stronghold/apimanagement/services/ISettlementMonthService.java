package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SettlementMonth;

@Service
public interface ISettlementMonthService {

	SettlementMonth getSettlementMonth(int id) throws ValidationException;

	List<SettlementMonth> getSettlementMonths(int year, int month, int idBuilding) throws ValidationException;

	void saveSettlementMonths(SettlementMonth settlementMonth) throws ValidationException;

	void updateSettlementMonths(SettlementMonth settlementMonth) throws ValidationException;

	void deleteSettlementMonths(SettlementMonth settlementMonth) throws ValidationException;

}

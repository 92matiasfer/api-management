package uy.com.stronghold.apimanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Transaction;
import uy.com.stronghold.apimanagement.models.UnitTransaction;

@Service
public interface ITransactionService {

	Optional<Transaction> getTransaction(int id) throws ValidationException;

	List<Transaction> getTransactions(int idBuilding, Date dateFrom, Date dateTo, 
			int settlementMonth, String type) throws ValidationException;

	void saveUnitTransaction(UnitTransaction transaction) throws ValidationException;

	void saveSupplierTransaction(SupplierTransaction transaction) throws ValidationException;

	void updateUnitTransaction(UnitTransaction transaction) throws ValidationException;

	void updateSupplierTransaction(SupplierTransaction transaction) throws ValidationException;

	void deleteById(int id);
	
	
	
}

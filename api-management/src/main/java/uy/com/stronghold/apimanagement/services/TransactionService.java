package uy.com.stronghold.apimanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Transaction;
import uy.com.stronghold.apimanagement.models.UnitTransaction;
import uy.com.stronghold.apimanagement.repositories.ITransactionRepository;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionRepository repository;
	
	@Override
	public Optional<Transaction> getTransaction(int id) throws ValidationException {
		Optional<Transaction> transaction = repository.getTransaction(id);
		if(transaction == null) throw new ValidationException(Errores.TRANSACTION_NOT_FOUND);
		return transaction;
	}

	@Override
	public List<Transaction> getTransactions(int idBuilding, Date dateFrom, Date dateTo,
			int settlementMonth, String type) throws ValidationException {
		List<Transaction> transactions = repository.getTransactions(idBuilding, dateFrom, dateTo, settlementMonth, type);
		if(transactions == null || transactions.isEmpty()) throw new ValidationException(Errores.TRANSACTION_NOT_FOUND);
		return transactions;
	
	}

	@Override
	public void saveUnitTransaction(UnitTransaction transaction) throws ValidationException {
		if(repository.save(transaction) == null)
			throw new ValidationException(Errores.ERROR_SAVE_TRANSACTION);
	}

	@Override
	public void saveSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		if(repository.save(transaction) == null)
			throw new ValidationException(Errores.ERROR_SAVE_TRANSACTION);
		
	}

	@Override
	public void updateUnitTransaction(UnitTransaction transaction) throws ValidationException {
		if(repository.save(transaction) == null)
			throw new ValidationException(Errores.ERROR_UPDATE_TRANSACTION);
	}

	@Override
	public void updateSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		if(repository.save(transaction) == null)
			throw new ValidationException(Errores.ERROR_UPDATE_TRANSACTION);
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(Long.parseLong(id+""));
	}

	
}

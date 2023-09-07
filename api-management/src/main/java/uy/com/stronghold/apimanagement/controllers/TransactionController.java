package uy.com.stronghold.apimanagement.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.enums.TransactionType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Transaction;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.models.UnitTransaction;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;
import uy.com.stronghold.apimanagement.utils.ValidationUtil;

public class TransactionController implements ITransactionController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	@Autowired
	ValidationUtil validation;
	
	@Override
	public ResponseEntity<Object> getTransaction(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		int id = validation.getInteger(idParam);
		Optional<Transaction> transaction = apiManagementServiceImp.getTransaction(id);
		return new ResponseEntity<Object>(transaction, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getTransactions(Optional<String> idBuildingParam, Optional<String> dateFromParam,
			Optional<String> dateToParam, Optional<String> typeParam, Optional<String> idSettlementMonthParam,
			HttpHeaders headers, HttpServletRequest request) throws ValidationException {
		
		List<Transaction> transactions = new ArrayList<>();
		//Normalizo y valido la entrada de los datos
		int idBuilding = validation.getInteger(idBuildingParam);
		int idSettlementMonth = validation.getInteger(idSettlementMonthParam);
		Date dateFrom = validation.getDate(dateFromParam);
		Date dateTo = validation.getDate(dateToParam);
		TransactionType type = validation.getTransactionType(typeParam);
		
		transactions = apiManagementServiceImp.getTransactions(idBuilding, dateFrom, dateTo, 
				idSettlementMonth, type.toString());
		
		return new ResponseEntity<Object>(transactions, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Object> saveUnitTransaction(UnitTransaction transaction, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		validation.validateSaveUnitTransaction(transaction);
		apiManagementServiceImp.SaveUnitTransaction(transaction);
		return new ResponseEntity<Object>("La Transacción ha sido creada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> saveSupplierTransaction(SupplierTransaction transaction, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		validation.validateSaveSupplierTransaction(transaction);		
		apiManagementServiceImp.saveSupplierTransaction(transaction);	
		return new ResponseEntity<Object>("La Transacción ha sido creada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateUnitTransaction(UnitTransaction transaction, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		validation.validateUpdateUnitTransaction(transaction);
		if(apiManagementServiceImp.getTransaction(transaction.getId()) == null) 
			throw new ValidationException(Errores.TRANSACTION_NOT_FOUND);
		
		apiManagementServiceImp.updateUnitTransaction(transaction);
		
		return new ResponseEntity<Object>("La Transacción ha sido modificada correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateSupplierTransaction(SupplierTransaction transaction, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		validation.validateUpdateSupplierTransaction(transaction);
		if(apiManagementServiceImp.getTransaction(transaction.getId()) == null) 
			throw new ValidationException(Errores.TRANSACTION_NOT_FOUND);
		
		apiManagementServiceImp.updateSupplierTransaction(transaction);
		
		return new ResponseEntity<Object>("La Transacción ha sido modificada correctamente", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Object> deleteTransaction(Optional<String> idParam, HttpHeaders headers,
			HttpServletRequest request) throws ValidationException {
		
		//Normalizo y valido la entrada de los datos
		int id = validation.getInteger(idParam);
		
		Optional<Transaction> transaction = apiManagementServiceImp.getTransaction(id);
		if(transaction == null) throw new ValidationException(Errores.TRANSACTION_NOT_FOUND);
		apiManagementServiceImp.deleteById(id);
		
		return new ResponseEntity<Object>("La Transacción ha sido eliminada correctamente", HttpStatus.OK);
	}

}

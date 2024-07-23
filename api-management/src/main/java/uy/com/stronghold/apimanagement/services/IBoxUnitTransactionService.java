package uy.com.stronghold.apimanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.BoxUnitTransaction;
import uy.com.stronghold.apimanagement.repositories.IBoxUnitTransactionRepository;

@Service
public interface IBoxUnitTransactionService {

    


    BoxUnitTransaction saveBoxUnitTransaction(BoxUnitTransaction but) throws ValidationException;

	BoxUnitTransaction updateBoxUnitTransaction(BoxUnitTransaction but) throws ValidationException;
	
	void deleteBoxUnitTransaction(BoxUnitTransaction but) throws ValidationException;

	
}

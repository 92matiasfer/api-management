package uy.com.stronghold.apimanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.BoxUnitTransaction;
import uy.com.stronghold.apimanagement.repositories.IBoxUnitTransactionRepository;

@Service
public class BoxUnitTransactionService implements IBoxUnitTransactionService {

    @Autowired
    private IBoxUnitTransactionRepository repository;


    @Override
    public BoxUnitTransaction saveBoxUnitTransaction(BoxUnitTransaction boxUnitTransaction) throws ValidationException {
       
        BoxUnitTransaction but = repository.save(boxUnitTransaction);
        if(but == null) throw new ValidationException(Errores.ERROR_SAVE_UNIT_BOX_TRANSACTION);
        return but;
    }

    @Override
    public BoxUnitTransaction updateBoxUnitTransaction(BoxUnitTransaction boxUnitTransaction) throws ValidationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBoxUnitTransaction'");
    }
    
    @Override
    public void deleteBoxUnitTransaction(BoxUnitTransaction but) throws ValidationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoxUnitTransaction'");
    }


   

    

}

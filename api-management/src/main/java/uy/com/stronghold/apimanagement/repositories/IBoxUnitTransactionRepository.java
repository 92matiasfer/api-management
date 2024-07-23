package uy.com.stronghold.apimanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.BoxUnitTransaction;


@Repository
public interface IBoxUnitTransactionRepository extends JpaRepository<BoxUnitTransaction, Integer> {

    


}

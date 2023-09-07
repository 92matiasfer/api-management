package uy.com.stronghold.apimanagement.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT t FROM transaction t WHERE t.id = :id")
	Optional<Transaction> getTransaction(int id);

	@Query("SELECT t FROM transaction t WHERE (:building IS NULL OR t.building = :building) AND (:dateFrom IS NULL OR t.date >= :dateFrom)"
			+ " AND (:dateTo IS NULL OR t.date <= :dateTo) AND (:settlementMonth IS NULL OR t.settlementMonth = :settlementMonth)"
			+ " AND (:transactionType IS NULL OR t.transactionType = :transactionType)")
	List<Transaction> getTransactions(int building, Date dateFrom, Date dateTo, 
			int settlementMonth, String transactionType);

}

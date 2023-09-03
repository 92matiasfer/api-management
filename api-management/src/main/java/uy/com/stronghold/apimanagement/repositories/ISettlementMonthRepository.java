package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.SettlementMonth;

@Repository
public interface ISettlementMonthRepository extends JpaRepository<SettlementMonth, Long> {

	@Query("SELECT m FROM settlement_month m WHERE m.id = :id")
	SettlementMonth getSettlementMonth(int id);

	@Query("SELECT m FROM settlement_month m WHERE (:year IS NULL OR m.year = :year) AND "
			+ "(:month IS NULL OR m.month = :month) AND (:building IS NULL OR m.building = :building)")
	List<SettlementMonth> getSettlementMonths(int year, int month, int building);

}

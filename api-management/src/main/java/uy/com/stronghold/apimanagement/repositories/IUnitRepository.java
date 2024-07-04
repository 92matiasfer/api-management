package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.models.Unit;

@Repository
public interface IUnitRepository extends JpaRepository<Unit, Integer>{

	@Query("SELECT u FROM unit u WHERE u.id = :id")
	Unit getUnit(int id);

//	@Query("SELECT u FROM unit u WHERE (:building IS NULL OR u.building = :building) AND (:number is NULL OR u.number = number)"
//			+ " AND (:unitType IS NULL OR u.unitType = :unitType)")
//	List<Unit> getUnits(int building, String number, String unitType);

	@Query("SELECT u "
			+ "FROM unit u "
			+ "WHERE (:building is 0 or u.building = :building) AND (:number is '' OR u.number = :number) "
			+ "AND (:unitType is NULL OR u.unitType = :unitType)")
	List<Unit> getUnits(int building, UnitType unitType, String number);
	

	
	
}

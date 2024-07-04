package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Building;

@Repository
public interface IBuildingRepository extends JpaRepository<Building, Integer> {


	@Query("SELECT b "
			+ "FROM building b "
			+ "WHERE b.id = :id")
	Building getBuilding(int id);
	
	@Query("SELECT b "
			+ "FROM building b "
			+ "WHERE (:name IS '' OR b.name = :name) ORDER BY b.name ASC")
	List<Building> getBuildings(String name);

}

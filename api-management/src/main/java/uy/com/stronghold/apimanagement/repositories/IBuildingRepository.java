package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Building;

@Repository
public interface IBuildingRepository extends JpaRepository<Building, Long> {

	
	@Query("SELECT b FROM building b WHERE (:id IS NULL OR b.id = :id) "
			+ "AND (:name IS NULL OR b.name = :name)")
	List<Building> getBuildings(int id, String name);
}

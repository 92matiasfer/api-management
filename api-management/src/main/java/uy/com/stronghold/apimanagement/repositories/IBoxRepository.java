package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Box;

@Repository
public interface IBoxRepository extends JpaRepository<Box, Integer> {

	@Query("SELECT b FROM box b WHERE b.id = :id")
	Box getBox(int id);

	@Query("SELECT b FROM box b WHERE (:name IS '' OR b.name = :name) AND "
			+ "(:building IS 0 OR b.building = :building)")
	List<Box> getBoxes(String name, int building);

	
}

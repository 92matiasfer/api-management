package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long>{

	@Query("SELECT s FROM supplier s WHERE s.id = :id")
	Supplier getSupplier(int id);

	@Query("SELECT s FROM supplier s WHERE (:name IS NULL OR s.name = :name)")
	List<Supplier> getSuppliers(String name);

	
	
}

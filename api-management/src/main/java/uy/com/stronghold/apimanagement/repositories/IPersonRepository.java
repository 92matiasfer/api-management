package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT p FROM person p WHERE p.id = :id")
	Person getPerson(int id);

	@Query("SELECT p FROM person p WHERE (:name IS NULL OR p.name = :name)")
	List<Person> getPersons(String name);

	
	
}

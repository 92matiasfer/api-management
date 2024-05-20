package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.Building;

@Repository
public class QueriesRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Building> getBuildings() {
        return entityManager.createQuery(
                "SELECT new uy.com.stronghold.apimanagement.models.Building(b.id, b.name) "
                + "FROM building b "
                + "ORDER BY b.name ASC",
                Building.class).getResultList();
    }
	
	
	
}

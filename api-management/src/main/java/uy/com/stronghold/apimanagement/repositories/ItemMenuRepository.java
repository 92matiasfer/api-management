package uy.com.stronghold.apimanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.models.ItemMenu;

@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenu, Integer>{

	@Query("SELECT m FROM items_menu m")
	List<ItemMenu> getItemsMenu();
}

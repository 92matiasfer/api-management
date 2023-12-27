package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.ItemMenu;
import uy.com.stronghold.apimanagement.repositories.ItemMenuRepository;

@Service
public class ItemMenuService implements IItemMenuService {

	@Autowired
	private ItemMenuRepository repository;
	
	@Override
	public List<ItemMenu> getItemsMenu() throws ValidationException {
		List<ItemMenu> ItemsMenu = null;
		ItemsMenu = repository.getItemsMenu();
		if(ItemsMenu == null || ItemsMenu.isEmpty()) throw new ValidationException(Errores.BUILDING_NOT_FOUND);
		return ItemsMenu;
	}

	
}

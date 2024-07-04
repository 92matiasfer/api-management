package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.ItemMenu;

@Service
public interface IItemMenuService {

	List<ItemMenu> getItemsMenu() throws ValidationException;
}

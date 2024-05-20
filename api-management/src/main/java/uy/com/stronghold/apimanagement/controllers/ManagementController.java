package uy.com.stronghold.apimanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.ItemMenu;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.resources.ApiManagementImp;

@Controller
public class ManagementController implements IManagementController {

	@Autowired
	ApiManagementImp apiManagementServiceImp;
	
	@Override
	public ResponseEntity<Object> getSystemStartup(HttpHeaders headers, HttpServletRequest request)
			throws ValidationException {
		
		List<ItemMenu> ItemsMenu = apiManagementServiceImp.getItemsMenu();
		List<Building> buildings = apiManagementServiceImp.getInitialBuildings(); 
		List<Supplier> suppliers = apiManagementServiceImp.getSuppliers("");
		
		Map<String, List<?>> dataMap = new HashMap<>();
        dataMap.put("itemsMenu", ItemsMenu);
        dataMap.put("buildings", buildings);
        dataMap.put("suppliers", suppliers);
		
		return new ResponseEntity<Object>(dataMap, HttpStatus.OK);
	}

}

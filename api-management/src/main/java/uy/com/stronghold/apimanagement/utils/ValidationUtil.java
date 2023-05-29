package uy.com.stronghold.apimanagement.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Building;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

@Component
public class ValidationUtil {

	/**
	 * Retorna un String con todos los parámetros ingresados por el usuario
	 * 
	 * @param parameters
	 * @return
	 */
	public String getRequestParamaters(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		//Obtengo los parámetros ingresados como @PathVariable
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		//Obtengo los parámetros ingresados como @@RequestParam
		Map<String, String[]> parameters = request.getParameterMap();
		//Cargo los @RequestParam en la respuesta
		for(String param : parameters.keySet()) {
			json.put(param, parameters.get(param)[0]);
		}
		//Cargo los @PathVariable en la respuesta
		if(!pathVariables.isEmpty()) {
			for(String param : pathVariables.keySet()) {
				json.put(param, pathVariables.get("id"));
			}
		}
		return json.toString();
	}
	
	
	public String normalizeInput(Optional<String> optional) {
		return optional.orElse("");
	}

	
	/**
	 *  Valida y retorna el valor númerico correspondiente al string ingresado
	 * 
	 * @param optional
	 * @return
	 * @throws ValidationException
	 */
	public int getInteger(Optional<String> optional) throws ValidationException {
		String param = optional.orElse("");
		return getInteger(param);
	}
	
	/**
	 * Valida y retorna el valor númerico correspondiente al string ingresado
	 * 
	 * @param param
	 * @return
	 * @throws ValidationException
	 */
	public int getInteger(String param) throws ValidationException {
		int retorno = 0;
		if(!StringUtils.isBlank(param)){
			if(isInteger(param)) {
				retorno = Integer.parseInt(param);
			} else {
				throw new ValidationException(Errores.INTEGER_INVALID);
			}
		}
		return retorno;
	}
	
	
	/**
	 * Valida que un String sea Integer
	 * @param cadena
	 * @return
	 */
	public static boolean isInteger(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * Valida que un String sea Double
	 * @param cadena
	 * @return
	 */
	public static boolean isDouble(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}


	public void validateSaveBuilding(Building building) throws ValidationException {
		if(building == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateBuilding(Building building) throws ValidationException {
		if(building == null || building.getId() <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}
}

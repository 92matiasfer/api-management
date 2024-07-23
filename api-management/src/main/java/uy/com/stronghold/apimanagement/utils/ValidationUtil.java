package uy.com.stronghold.apimanagement.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.enums.TransactionType;
import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Box;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.Person;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.models.UnitTransaction;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

	public double getDouble(Optional<String> optional) throws ValidationException {
		String param = optional.orElse("");
		return getDouble(param);
	}

	public double getDouble(String param) throws ValidationException {
		double retorno = 0;
		if(!StringUtils.isBlank(param)){
			if(isDouble(param)) {
				retorno = Double.parseDouble(param);
			} else {
				throw new ValidationException(Errores.DOUBLE_INVALID);
			}
		}
		return retorno;
	}

	public float getFloat(Optional<String> optional) throws ValidationException {
        String param = optional.orElse("");
        return getFloat(param);
    } 
	
	
	public float getFloat(String param) throws ValidationException {
		float retorno = 0;
		if (!StringUtils.isBlank(param)) {
			if (isFloat(param)) {
				retorno = Float.parseFloat(param);
			} else {
				throw new ValidationException(Errores.FLOAT_INVALID);
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

	public static boolean isFloat(String cadena){
		try {
			Float.parseFloat(cadena);
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

	public void validateSaveUnit(Unit unit) throws ValidationException {
		if(unit == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}
	
	public void validateUpdateUnit(Unit unit) throws ValidationException {
		if(unit == null || unit.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}

	public UnitType getUnitType(Optional<String> unitTypeParam) throws ValidationException {
		String unitType = unitTypeParam.orElse("");
		return getUnitType(unitType);
	}


	private UnitType getUnitType(String unitType) throws ValidationException {
		if(!StringUtils.isBlank(unitType)) {
			if(unitType.equalsIgnoreCase(UnitType.renter.toString())) {
				return UnitType.renter;
			} else if(unitType.equalsIgnoreCase(UnitType.owner.toString())) {
				return UnitType.owner;
			} else if(unitType.equalsIgnoreCase(UnitType.both.toString())) {
				return UnitType.both;
			} else {
				throw new ValidationException(Errores.UNIT_TYPE_INVALID);
			}
		} else return null;
	}


	public SettlementMonth getSettlementMonth(Optional<String> idSettlementMonthParam) {
		// TODO Auto-generated method stub
		return null;
	}


	public TransactionType getTransactionType(Optional<String> typeParam) {
		// TODO Auto-generated method stub
		return null;
	}


	public Date getDate(Optional<String> dateFromParam) {
		// TODO Auto-generated method stub
		return null;
	}


	public void validateSaveUnitTransaction(UnitTransaction transaction) throws ValidationException {
		if(transaction == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateSaveSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		if(transaction == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateUnitTransaction(UnitTransaction transaction) throws ValidationException {
		if(transaction == null || transaction.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		if(transaction == null || transaction.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateSaveBox(Box box) throws ValidationException {
		if(box == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateBox(Box box) throws ValidationException {
		if(box == null || box.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateSaveSupplier(Supplier supplier) throws ValidationException {
		if(supplier == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateSupplier(Supplier supplier) throws ValidationException {
		if(supplier == null || supplier.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateSavePerson(Person person) throws ValidationException {
		if(person == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdatePerson(Person person) throws ValidationException {
		if(person == null || person.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateSaveSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		if(settlementMonth == null) throw new ValidationException(Errores.CAMPOS_NULL);
	}


	public void validateUpdateSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		if(settlementMonth == null || settlementMonth.getId()  <= 0) throw new ValidationException(Errores.CAMPOS_NULL);
	}


    



	
}

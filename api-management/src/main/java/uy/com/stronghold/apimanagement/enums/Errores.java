package uy.com.stronghold.apimanagement.enums;

import org.springframework.http.HttpStatus;

import uy.com.stronghold.apimanagement.exceptions.ErroresInterface;

public enum Errores implements ErroresInterface {

	ERROR_INTERNO(1, "1", "Error Interno", HttpStatus.INTERNAL_SERVER_ERROR), 
	
	ERROR_SAVE_BUILDING(1, "1", "El edificio no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_UNIT(1, "1", "La unidad no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_BOX(1, "1", "La caja no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_TRANSACTION(1, "1", "La Transaccion no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_UNIT_BOX_TRANSACTION(1, "1", "La Transacción de la unidad para la caja indicada no se ha podido guardar correctamente", HttpStatus.NOT_FOUND), 
	ERROR_SAVE_SUPPLIER(1, "1", "El Proveedor no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_PERSON(1, "1", "La Persona no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_SAVE_SETTLEMENT_MONTH(1, "1", "El Mes Liquidacion no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	
	SUPPLIER_ALREADY_EXISTS(1, "1", "El Proveedor ya existe", HttpStatus.BAD_REQUEST),

	ERROR_UPDATE_TRANSACTION(1, "1", "La Transaccion no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_BOX(1, "1", "La Caja no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_UNIT(1, "1", "La Unidad no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_BUILDING(1, "1", "El Edificio no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_SUPPLIER(1, "1", "El Proveedor no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_PERSON(1, "1", "La Persona no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_UPDATE_SETTLEMENT_MONTH(1, "1", "El Mes Liquidacion no se ha podido modificar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	
	ERROR_GET_BOX_SETTLEMENT_MONTH(1, "1", "La unidad de la caja del mes liquidación no se pudo cargar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),

	CAMPOS_NULL(2, "2", "Campos nulos o vacíos. Debe ingresar algun valor.", HttpStatus.BAD_REQUEST),
	TYPE_INVALID(2, "3", "El tipo ingresado no es valido.", HttpStatus.BAD_REQUEST),
	INTEGER_INVALID(14, "14", "Información ingresada para campo numérico no valido.", HttpStatus.BAD_REQUEST),
	DOUBLE_INVALID(14, "14", "Información ingresada para campo numérico no valido.", HttpStatus.BAD_REQUEST),
	FLOAT_INVALID(14, "14", "Información ingresada para campo numérico no valido.", HttpStatus.BAD_REQUEST),
	UNIT_TYPE_INVALID(14, "14", "Tipo de unidad no valida", HttpStatus.BAD_REQUEST),
	
	BUILDING_NOT_FOUND(1, "1", "Edificio no encontrado", HttpStatus.NOT_FOUND),
	REGISTRO_NOT_FOUND(12, "4", "El registro no existe", HttpStatus.NOT_FOUND),
	UNIT_NOT_FOUND(1, "1", "Unidad no encontrado", HttpStatus.NOT_FOUND), 
	TRANSACTION_NOT_FOUND(1, "1", "Transacción no encontrada", HttpStatus.NOT_FOUND), 
	BOX_NOT_FOUND(1, "1", "Caja no encontrada", HttpStatus.NOT_FOUND), 
	PERSON_NOT_FOUND(1, "1", "Persona no encontrada", HttpStatus.NOT_FOUND), 
	SUPPLIER_NOT_FOUND(1, "1", "Proveedor no encontrado", HttpStatus.NOT_FOUND),
	SETTLEMENT_MONTH_NOT_FOUND(1, "1", "Mes Liquidacion no encontrado", HttpStatus.NOT_FOUND), 
	
	;
	

	private int codigo;
	private String codigoError;
	private String mensajeError;
	private HttpStatus httpStatus;

	private Errores(int codigo, String codigoError, String mensajeError, HttpStatus httpStatus) {
		this.codigo = codigo;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
		this.httpStatus = httpStatus;
	}

	@Override
	public int getCodigo() {
		return codigo;
	}

	@Override
	public String getCodigoError() {
		return codigoError;
	}

	@Override
	public String getMensajeError() {
		return mensajeError;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

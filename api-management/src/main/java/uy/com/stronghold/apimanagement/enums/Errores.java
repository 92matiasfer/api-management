package uy.com.stronghold.apimanagement.enums;

import org.springframework.http.HttpStatus;

import uy.com.stronghold.apimanagement.exceptions.ErroresInterface;

public enum Errores implements ErroresInterface {

	ERROR_INTERNO(1, "1", "Error Interno", HttpStatus.INTERNAL_SERVER_ERROR), 
	ERROR_SAVE_BUILDING(1, "1", "El edificio no se ha podido guardar correctamente", HttpStatus.INTERNAL_SERVER_ERROR),
	
	CAMPOS_NULL(2, "2", "Campos nulos o vacíos. Debe ingresar algun valor.", HttpStatus.BAD_REQUEST),
	TYPE_INVALID(2, "3", "El tipo ingresado no es valido.", HttpStatus.BAD_REQUEST),
	INTEGER_INVALID(14, "14", "Información ingresada para campo numérico no valido.", HttpStatus.BAD_REQUEST),
	
	BUILDING_NOT_FOUND(1, "1", "Edificio no encontrado", HttpStatus.NOT_FOUND),
	REGISTRO_NOT_FOUND(12, "4", "El registro no existe", HttpStatus.NOT_FOUND);
	

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

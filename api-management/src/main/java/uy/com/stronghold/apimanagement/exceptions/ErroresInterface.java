package uy.com.stronghold.apimanagement.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public interface ErroresInterface extends Serializable{

	public int getCodigo();
	public String getCodigoError();
	public String getMensajeError();
	public HttpStatus getHttpStatus();
}

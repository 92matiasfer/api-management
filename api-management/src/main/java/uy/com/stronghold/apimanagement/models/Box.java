package uy.com.stronghold.apimanagement.models;

public class Box {

	private int id;
	private String nombre;
	private int calculationType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(int calculationType) {
		this.calculationType = calculationType;
	}
	
	public Box(int id, String nombre, int calculationType) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.calculationType = calculationType;
	}
	
	
	
	
	
	
}

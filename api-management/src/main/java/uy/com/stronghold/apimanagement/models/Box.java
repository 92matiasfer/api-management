package uy.com.stronghold.apimanagement.models;

public class Box {

	private int id;
	private String name;
	private int calculationType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(int calculationType) {
		this.calculationType = calculationType;
	}
	
	public Box(int id, String name, int calculationType) {
		super();
		this.id = id;
		this.name = name;
		this.calculationType = calculationType;
	}
	
	
	
	
	
	
}

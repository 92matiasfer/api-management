package uy.com.stronghold.apimanagement.models;

public class Box {

	private int id;
	private String name;
	private CalculationMethod calculationMethod;
	private Building building;
	
	
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

	public CalculationMethod getCalculationMethod() {
		return calculationMethod;
	}
	public void setCalculationMethod(CalculationMethod calculationMethod) {
		this.calculationMethod = calculationMethod;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	

	
	public Box(int id, String name, CalculationMethod calculationMethod, Building building) {
		super();
		this.id = id;
		this.name = name;
		this.calculationMethod = calculationMethod;
		this.building = building;
	}
	
	public Box() {
		super();
	}
	
	

	
	
	
	
	
	
}

package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "box")
@Table(name = "box", schema = "juncal_management")
public class Box {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@ManyToOne
    @JoinColumn(name = "id_calculation_method")
	private CalculationMethod calculationMethod;
	@ManyToOne
    @JoinColumn(name = "id_building")
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
	

	public Box() {
		super();
	}
	public Box(int id, String name, CalculationMethod calculationMethod, Building building) {
		super();
		this.id = id;
		this.name = name;
		this.calculationMethod = calculationMethod;
		this.building = building;
	}

	
	
}

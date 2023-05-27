package uy.com.stronghold.apimanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uy.com.stronghold.apimanagement.enums.UnitType;

@Entity(name = "unit")
@Table(name = "unit", schema = "juncal_management")
public class Unit {

	@Id
	@Column(name = "id")
	private int id;
	@ManyToOne
    @JoinColumn(name = "id_building")
	private Building building;
    @Column(name = "number")
	private String number;
    @Column(name = "coefficient")
	private float coefficient;
    @Column(name = "area")
	private float area; 
//	private UnitType unitType;
//	@JoinColumn(name = "id_person")
//	private Person person;	
	
	
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
//	public Person getPerson() {
//		return person;
//	}
//	public void setPerson(Person person) {
//		this.person = person;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
//	public UnitType getUnitType() {
//		return unitType;
//	}
//	public void setUnitType(UnitType unitType) {
//		this.unitType = unitType;
//	}
	
	public Unit() {
		super();
	}
	public Unit(int id, Building building, String number, float coefficient, float area) {
		super();
		this.id = id;
		this.building = building;
		this.number = number;
		this.coefficient = coefficient;
		this.area = area;
//		this.unitType = unitType;
//		this.person = person;
	}

	
	
}

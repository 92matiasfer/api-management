package uy.com.stronghold.apimanagement.models;

import uy.com.stronghold.apimanagement.enums.UnitType;

public class Unit {

	private int id;
	private Building building;
	private String number;
	private float coefficient;
	private UnitType unitType;
	
	
	
	
	
	
	
	
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
	public UnitType getUnitType() {
		return unitType;
	}
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
	public Unit() {
		super();
	}
	public Unit(int id, Building building, String number, float coefficient, UnitType unitType) {
		super();
		this.id = id;
		this.building = building;
		this.number = number;
		this.coefficient = coefficient;
		this.unitType = unitType;
	}
	
}

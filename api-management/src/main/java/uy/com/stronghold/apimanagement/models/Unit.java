package uy.com.stronghold.apimanagement.models;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.utils.UnitTypeConverter;

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
    @Column(name = "reference")
	private String reference; 
//    @Convert(converter = UnitTypeConverter.class)
//    @Column(name = "unit_type")
//	private UnitType unitType;
	@OneToOne
	@JoinColumn(name = "id_person")
	private Person person;	
	
	
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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Unit() {
		super();
	}
	public Unit(int id, Building building, String number, float coefficient, float area, String reference,
			Person person) {
		super();
		this.id = id;
		this.building = building;
		this.number = number;
		this.coefficient = coefficient;
		this.area = area;
		this.reference = reference;
		this.person = person;
	}
//	public Unit(int id, Building building, String number, float coefficient, float area, String reference,
//			UnitType unitType, Person person) {
//		super();
//		this.id = id;
//		this.building = building;
//		this.number = number;
//		this.coefficient = coefficient;
//		this.area = area;
//		this.reference = reference;
//		this.unitType = unitType;
//		this.person = person;
//	}

	
}
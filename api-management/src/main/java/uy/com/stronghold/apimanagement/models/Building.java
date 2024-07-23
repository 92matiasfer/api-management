package uy.com.stronghold.apimanagement.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "building")
@Table(name = "building", schema = "juncal_management")
public class Building {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "year_built")
	private int yearBuilt;
	@Column(name = "meters_built")
	private float metersBuilt;
	@Column(name = "total_meters")
	private float totalMeters;
	@OneToMany(mappedBy = "building")
	private List<Unit> units;
	@OneToMany(mappedBy = "building")
	private List<Box> boxes;
	@JsonIgnore
	@OneToMany(mappedBy = "building")
	private List<SettlementMonth> settlementMonths;

	@ManyToMany
    @JoinTable(
        name = "building_supplier",
        joinColumns = @JoinColumn(name = "id_building"),
        inverseJoinColumns = @JoinColumn(name = "id_supplier")
    )
    private Set<Supplier> suppliers = new HashSet<>();

	
	
	@Transient
	private int value;
	@Transient
	private String label;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.value = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.label = name; 
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getYearBuilt() {
		return yearBuilt;
	}
	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	public float getMetersBuilt() {
		return metersBuilt;
	}
	public void setMetersBuilt(float metersBuilt) {
		this.metersBuilt = metersBuilt;
	}
	public float getTotalMeters() {
		return totalMeters;
	}
	public void setTotalMeters(float totalMeters) {
		this.totalMeters = totalMeters;
	}
	public List<Unit> getUnits() {
		return units;
	}
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	public List<SettlementMonth> getSettlementMonths() {
		return settlementMonths;
	}
	public void setSettlementMonths(List<SettlementMonth> settlementMonths) {
		this.settlementMonths = settlementMonths;
	}
	public int getValue() {
		return id;
	}
	public String getLabel() {
		return name;
	}
	public List<Box> getBoxes() {
		return boxes;
	}
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
	public Set<Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	
	public Building() {
		super();
	}
	
	public Building(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Building(int id, String name, String address, int yearBuilt, float metersBuilt, float totalMeters,
			List<Unit> units, List<Box> boxes, List<SettlementMonth> settlementMonths, Set<Supplier> suppliers,
			int value, String label) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.yearBuilt = yearBuilt;
		this.metersBuilt = metersBuilt;
		this.totalMeters = totalMeters;
		this.units = units;
		this.boxes = boxes;
		this.settlementMonths = settlementMonths;
		this.suppliers = suppliers;
		this.value = value;
		this.label = label;
	}
	
}

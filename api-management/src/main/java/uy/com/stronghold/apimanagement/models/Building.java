package uy.com.stronghold.apimanagement.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JsonIgnore
	@OneToMany(mappedBy = "building")
	private List<Unit> units;
	@JsonIgnore
	@OneToMany(mappedBy = "building")
	private List<SettlementMonth> settlementMonths = new ArrayList<>();
	
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
	
	
	
	public Building() {
		super();
	}
	
	public Building(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Building(int id, String name, String address, int yearBuilt, float metersBuilt, float totalMeters,
			List<Unit> units, List<SettlementMonth> settlementMonths) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.yearBuilt = yearBuilt;
		this.metersBuilt = metersBuilt;
		this.totalMeters = totalMeters;
		this.units = units;
		this.settlementMonths = settlementMonths;
		this.value = id;
		this.label = name;
	}

}

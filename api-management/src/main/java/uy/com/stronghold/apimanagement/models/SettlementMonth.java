package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "settlement_month")
@Table(name = "settlement_month", schema = "juncal_management")
public class SettlementMonth {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "year")
	private int year;
	@Column(name = "month")
	private int month;
	@Column(name = "previousBalance")
	private float previousBalance;
	@Column(name = "currentBalance")
	private float currentBalance;
	@ManyToOne
	@JoinColumn(name = "id_building")
	private Building building;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public float getPreviousBalance() {
		return previousBalance;
	}
	public void setPreviousBalance(float previousBalance) {
		this.previousBalance = previousBalance;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	
	public SettlementMonth(int id, int year, int month, float previousBalance, 
			float currentBalance, Building building) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.building = building;
	}
	
	public SettlementMonth() {
		super();
	}
	
	

}

package uy.com.stronghold.apimanagement.models;

public class SettlementMonth {
	
	private int id;
	private int year;
	private int month;
	private float previousBalance;
	private float currentBalance;
	private Building bulding;
	
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

	public Building getBulding() {
		return bulding;
	}
	public void setBulding(Building bulding) {
		this.bulding = bulding;
	}
	
	
	public SettlementMonth(int id, int year, int month, float previousBalance, float currentBalance, Building bulding) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.bulding = bulding;
	}
	
	public SettlementMonth() {
		super();
	}
	
	

}

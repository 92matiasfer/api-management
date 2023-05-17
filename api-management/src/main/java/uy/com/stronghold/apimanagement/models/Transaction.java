package uy.com.stronghold.apimanagement.models;

import java.util.Date;

import uy.com.stronghold.apimanagement.enums.TransactionType;

public class Transaction {
	
	private int id;
	private	Date date;
	private float totalAmount;
	private String description;
	private TransactionType transactionType;
	private Building building;
	private SettlementMonth settlementMonth;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public SettlementMonth getSettlementMonth() {
		return settlementMonth;
	}
	public void setSettlementMonth(SettlementMonth settlementMonth) {
		this.settlementMonth = settlementMonth;
	}
	
	public Transaction(int id, Date date, float totalAmount, String description, TransactionType transactionType,
			Building building, SettlementMonth settlementMonth) {
		super();
		this.id = id;
		this.date = date;
		this.totalAmount = totalAmount;
		this.description = description;
		this.transactionType = transactionType;
		this.building = building;
		this.settlementMonth = settlementMonth;
	}
	public Transaction() {
		super();
	}
	
	

}

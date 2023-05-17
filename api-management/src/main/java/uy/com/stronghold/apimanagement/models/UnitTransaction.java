package uy.com.stronghold.apimanagement.models;

import java.util.List;

public class UnitTransaction {
	
	private int id;
	private Unit unit;
	private List<BoxUnitTransaction> boxUnitTransactions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public List<BoxUnitTransaction> getBoxUnitTransactions() {
		return boxUnitTransactions;
	}
	public void setBoxUnitTransactions(List<BoxUnitTransaction> boxUnitTransactions) {
		this.boxUnitTransactions = boxUnitTransactions;
	}
	
	public UnitTransaction(int id, Unit unit, List<BoxUnitTransaction> boxUnitTransactions) {
		super();
		this.id = id;
		this.unit = unit;
		this.boxUnitTransactions = boxUnitTransactions;
	}
	public UnitTransaction() {
		super();
	}
	
	
	

}

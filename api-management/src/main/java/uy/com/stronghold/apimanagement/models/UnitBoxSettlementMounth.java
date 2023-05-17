package uy.com.stronghold.apimanagement.models;

public class UnitBoxSettlementMounth {

	private int id;
	private float previousBalance;
	private float currentBalance;
	private BoxSettlementMonth boxSettlementMonth;
	private Unit unit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public BoxSettlementMonth getBoxSettlementMonth() {
		return boxSettlementMonth;
	}
	public void setBoxSettlementMonth(BoxSettlementMonth boxSettlementMonth) {
		this.boxSettlementMonth = boxSettlementMonth;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public UnitBoxSettlementMounth(int id, float previousBalance, float currentBalance,
			BoxSettlementMonth boxSettlementMonth, Unit unit) {
		super();
		this.id = id;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.boxSettlementMonth = boxSettlementMonth;
		this.unit = unit;
	}
	public UnitBoxSettlementMounth() {
		super();
	}
	
	
}

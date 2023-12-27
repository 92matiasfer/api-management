package uy.com.stronghold.apimanagement.models;

public class BoxSettlementMonth {

	private int id;
	private float previousBalance;
	private float currentBalance;
	private Box box;
	private SettlementMonth settlementMonth;
	
	
	
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
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public SettlementMonth getSettlementMonth() {
		return settlementMonth;
	}
	public void setSettlementMonth(SettlementMonth settlementMonth) {
		this.settlementMonth = settlementMonth;
	}
	
	public BoxSettlementMonth(int id, float previousBalance, float currentBalance, Box box,
			SettlementMonth settlementMonth) {
		super();
		this.id = id;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.box = box;
		this.settlementMonth = settlementMonth;
	}
	public BoxSettlementMonth() {
		super();
	}
	
	
}

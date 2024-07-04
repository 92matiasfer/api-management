package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "unit_box_settlement_month")
@Table(name = "unit_box_settlement_month", schema = "juncal_management")
public class UnitBoxSettlementMonth {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "amount_settlement_month")
	private float amountSettlementMonth;
	@Column(name = "previous_balance")
	private float previousBalance;
	@Column(name = "current_balance")
	private float currentBalance;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_box_settlement_month")
	private BoxSettlementMonth boxSettlementMonth;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_unit")
	private Unit unit;

	@javax.persistence.Transient
	private String boxName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmountSettlementMonth() {
		return amountSettlementMonth;
	}
	public void setAmountSettlementMonth(float amountSettlementMonth) {
		this.amountSettlementMonth = amountSettlementMonth;
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
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public UnitBoxSettlementMonth() {
		super();
	}
	public UnitBoxSettlementMonth(int id, float amountSettlementMonth, float previousBalance, float currentBalance,
			BoxSettlementMonth boxSettlementMonth, Unit unit, String boxName) {
		this.id = id;
		this.amountSettlementMonth = amountSettlementMonth;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.boxSettlementMonth = boxSettlementMonth;
		this.unit = unit;
		this.boxName = boxName;
	}
}

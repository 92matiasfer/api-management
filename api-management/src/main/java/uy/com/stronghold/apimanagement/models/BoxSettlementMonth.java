package uy.com.stronghold.apimanagement.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "box_settlement_month")
@Table(name = "box_settlement_month", schema = "juncal_management")
public class BoxSettlementMonth {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "previous_balance")
	private float previousBalance;
	@Column(name = "current_balance")
	private float currentBalance;
	@ManyToOne
    @JoinColumn(name = "id_box")
	private Box box;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_settlement_month")
	private SettlementMonth settlementMonth;
	@OneToMany(mappedBy = "boxSettlementMonth")
	private List<UnitBoxSettlementMonth> unitsboxesSettlementMonths;
	
	
	
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
	public List<UnitBoxSettlementMonth> getUnitsboxesSettlementMonths() {
		return unitsboxesSettlementMonths;
	}
	public void setUnitsboxesSettlementMonths(List<UnitBoxSettlementMonth> unitsboxesSettlementMonths) {
		this.unitsboxesSettlementMonths = unitsboxesSettlementMonths;
	}
	
	
	public BoxSettlementMonth() {
		super();
	}
	public BoxSettlementMonth(int id, float previousBalance, float currentBalance, Box box,
			SettlementMonth settlementMonth, List<UnitBoxSettlementMonth> unitsboxesSettlementMonths) {
		super();
		this.id = id;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.box = box;
		this.settlementMonth = settlementMonth;
		this.unitsboxesSettlementMonths = unitsboxesSettlementMonths;
	}
	
}

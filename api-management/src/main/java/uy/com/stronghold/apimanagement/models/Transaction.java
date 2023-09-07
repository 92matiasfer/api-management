package uy.com.stronghold.apimanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name = "transaction")
@Table(name = "transaction", schema = "juncal_management")
public class Transaction {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "date")
	private	Date date;
	@Column(name = "total_amount")
	private float totalAmount;
	@Column(name = "description")
	private String description;
	@Column(name = "transaction_type")
	private String transactionType;
	@ManyToOne
    @JoinColumn(name = "id_building")
	private Building building;
	@ManyToOne
    @JoinColumn(name = "id_settlement_month")
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
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
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
	
	public Transaction() {
		super();
	}
	public Transaction(int id, Date date, float totalAmount, String description, String transactionType,
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

}

package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "box_unit_transaction")
@Table(name = "box_unit_transaction", schema = "juncal_management")
public class BoxUnitTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@ManyToOne
    @JoinColumn(name = "id_box")
	private Box box;
	@Column(name = "amount")
	private float amount;
	@ManyToOne
    @JoinColumn(name = "id_unit_transaction")
	private UnitTransaction unitTransaction;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public UnitTransaction getUnitTransaction() {
		return unitTransaction;
	}
	public void setUnitTransaction(UnitTransaction unitTransaction) {
		this.unitTransaction = unitTransaction;
	}
	

	public BoxUnitTransaction() {
		super();
	}
	public BoxUnitTransaction(int id, Box box, float amount, UnitTransaction unitTransaction) {
		this.id = id;
		this.box = box;
		this.amount = amount;
		this.unitTransaction = unitTransaction;
	}
	
	
	

}

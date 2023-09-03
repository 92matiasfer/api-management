package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "box_unit_transaction")
@Table(name = "box_unit_transaction", schema = "juncal_management")
public class BoxUnitTransaction {
	
	@Id
	@Column(name = "id")
	private int id;
	@ManyToOne
    @JoinColumn(name = "id_box")
	private Box box;
	@Column(name = "mount")
	private float mount;
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
	public float getMount() {
		return mount;
	}
	public void setMount(float mount) {
		this.mount = mount;
	}
	public UnitTransaction getUnitTransaction() {
		return unitTransaction;
	}
	public void setUnitTransaction(UnitTransaction unitTransaction) {
		this.unitTransaction = unitTransaction;
	}
	
	public BoxUnitTransaction(int id, Box box, float mount, UnitTransaction unitTransaction) {
		super();
		this.id = id;
		this.box = box;
		this.mount = mount;
		this.unitTransaction = unitTransaction;
	}
	public BoxUnitTransaction() {
		super();
	}
	
	

}

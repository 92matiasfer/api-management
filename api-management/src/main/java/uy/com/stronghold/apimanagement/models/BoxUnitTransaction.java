package uy.com.stronghold.apimanagement.models;

public class BoxUnitTransaction {
	
	private int id;
	private Box box;
	private float mount;
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

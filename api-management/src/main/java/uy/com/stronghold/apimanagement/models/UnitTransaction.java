package uy.com.stronghold.apimanagement.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "unit_transaction")
@Table(name = "unit_transaction", schema = "juncal_management")
public class UnitTransaction extends Transaction {
	
	@Id
	@Column(name = "id")
	private int id;
	@ManyToOne
    @JoinColumn(name = "id_unit")
	private Unit unit;
	@OneToMany(mappedBy = "unitTransaction")
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

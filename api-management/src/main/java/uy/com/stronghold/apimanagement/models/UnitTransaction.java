package uy.com.stronghold.apimanagement.models;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "unit_transaction")
@Table(name = "unit_transaction", schema = "juncal_management")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("U")
public class UnitTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "id_unit")
    private Unit unit;

    @OneToMany(mappedBy = "unitTransaction")
    private List<BoxUnitTransaction> boxUnitTransactions;
	
	
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

	
	public UnitTransaction() {
		super();
	}
	public UnitTransaction(int id, Date date, float totalAmount, String description, String transactionType,
			Building building, SettlementMonth settlementMonth, Unit unit,
			List<BoxUnitTransaction> boxUnitTransactions) {
		super(id, date, totalAmount, description, transactionType, building, settlementMonth);
		this.unit = unit;
		this.boxUnitTransactions = boxUnitTransactions;
	}
	
}

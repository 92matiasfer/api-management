package uy.com.stronghold.apimanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity(name = "supplier_transaction")
@Table(name = "supplier_transaction", schema = "juncal_management")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("P")
public class SupplierTransaction extends Transaction {
	
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	@ManyToOne
    @JoinColumn(name = "id_supplier")
	private Supplier supplier;
	@ManyToOne
    @JoinColumn(name = "id_box")
	private Box box;
	
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	
	
	public SupplierTransaction() {
		super();
	}
	public SupplierTransaction(int id, Date date, float totalAmount, String description, String transactionType,
			Building building, SettlementMonth settlementMonth, String invoiceNumber, Supplier supplier, Box box) {
		super(id, date, totalAmount, description, transactionType, building, settlementMonth);
		this.invoiceNumber = invoiceNumber;
		this.supplier = supplier;
		this.box = box;
	}

	
	

}

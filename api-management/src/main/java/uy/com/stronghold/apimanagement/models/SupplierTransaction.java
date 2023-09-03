package uy.com.stronghold.apimanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uy.com.stronghold.apimanagement.enums.TransactionType;

@Entity(name = "supplier_transaction")
@Table(name = "supplier_transaction", schema = "juncal_management")
public class SupplierTransaction extends Transaction {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "invoice_number")
	private String invoiceNumber;
	@ManyToOne
    @JoinColumn(name = "id_supplier")
	private Supplier supplier;
	@ManyToOne
    @JoinColumn(name = "id_box")
	private Box box;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public SupplierTransaction(int id, String invoiceNumber, Supplier supplier, Box box) {
		super();
		this.id = id;
		this.invoiceNumber = invoiceNumber;
		this.supplier = supplier;
		this.box = box;
	}
	public SupplierTransaction() {
		super();
	}

	
	

}

package uy.com.stronghold.apimanagement.models;

import java.util.Date;

import uy.com.stronghold.apimanagement.enums.TransactionType;

public class SupplierTransaction extends Transaction {
	
	private int id;
	private String invoiceNumber;
	private Supplier supplier;
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

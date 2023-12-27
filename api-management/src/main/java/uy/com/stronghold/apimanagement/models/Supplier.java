package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "supplier")
@Table(name = "supplier", schema = "juncal_management")
public class Supplier {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "rut")
	private String rut;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	
	@Transient
	private int value;
	@Transient
	private String label;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.value = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.label = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getValue() {
		return id;
	}
	public String getLabel() {
		return name;
	}
	
	
	public Supplier() {
		super();
	}
	public Supplier(int id, String name, String description, String rut, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.rut = rut;
		this.phone = phone;
		this.email = email;
		this.value = id;
		this.label = name;
	}
	
	
	

}

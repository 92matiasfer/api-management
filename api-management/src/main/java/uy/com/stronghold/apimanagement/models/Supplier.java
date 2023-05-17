package uy.com.stronghold.apimanagement.models;

public class Supplier {
	
	private int id;
	private String name;
	private String description;
	private String rut;
	private String phone;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Supplier(int id, String name, String description, String rut, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.rut = rut;
		this.phone = phone;
		this.email = email;
	}
	public Supplier() {
		super();
	}
	
	

}

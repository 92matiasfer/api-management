package uy.com.stronghold.apimanagement.models;

public class Person {
	
	private int id;
	private String name;
	private String ci;
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


	public String getCi() {
		return ci;
	}


	public void setCi(String ci) {
		this.ci = ci;
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


	public Person(int id, String name, String ci, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.ci = ci;
		this.phone = phone;
		this.email = email;
	}


	public Person() {
		super();
	}
		

}

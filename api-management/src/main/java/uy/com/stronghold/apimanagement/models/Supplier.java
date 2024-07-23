package uy.com.stronghold.apimanagement.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@ManyToMany(mappedBy = "suppliers")
	@JsonIgnore
    private Set<Building> buildings = new HashSet<>();
	
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
	public Set<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}
	
	
	public Supplier() {
		super();
	}
	public Supplier(int id, String name, String description, String rut, String phone, String email,
			Set<Building> buildings, int value, String label) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rut = rut;
		this.phone = phone;
		this.email = email;
		this.buildings = buildings;
		this.value = value;
		this.label = label;
	}
	
	
}

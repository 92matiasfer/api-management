package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "calculation_method")
@Table(name = "calculation_method", schema = "juncal_management")
public class CalculationMethod {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "description")
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CalculationMethod(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	public CalculationMethod() {
		super();
	}
	
	
}

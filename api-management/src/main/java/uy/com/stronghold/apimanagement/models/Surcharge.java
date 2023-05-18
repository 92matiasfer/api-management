package uy.com.stronghold.apimanagement.models;

public class Surcharge {
	
	private int id;
	private String description;
	private float percentage;
	private int day;
	private Building building;
	
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
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}	
	
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	
	
	public Surcharge(int id, String description, float percentage, int day, Building building) {
		super();
		this.id = id;
		this.description = description;
		this.percentage = percentage;
		this.day = day;
		this.building = building;
	}
	
	public Surcharge() {
		super();
	}
	
	
	
	
	
	

}

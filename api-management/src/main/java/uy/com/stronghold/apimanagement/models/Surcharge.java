package uy.com.stronghold.apimanagement.models;

public class Surcharge {
	
	private int id;
	private String description;
	private float percentage;
	private int dayDeadline;
	
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
	public int getDayDeadline() {
		return dayDeadline;
	}
	public void setDayDeadline(int dayDeadline) {
		this.dayDeadline = dayDeadline;
	}
	
	public Surcharge(int id, String description, float percentage, int dayDeadline) {
		super();
		this.id = id;
		this.description = description;
		this.percentage = percentage;
		this.dayDeadline = dayDeadline;
	}
	
	public Surcharge() {
		super();
	}
	
	
	
	
	
	

}

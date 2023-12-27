package uy.com.stronghold.apimanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "items_menu")
@Table(name = "items_menu", schema = "juncal_management")
public class ItemMenu {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "titulo")
	private String title;
	@Column(name = "icono")
	private String icon;
	@Column(name = "mostrar_cop")
	@JsonProperty("mostrar")
	private int showCop;
	@Column(name = "orden")
	private int order;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		this.label = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getShowCop() {
		return showCop;
	}
	public void setShowCop(int showCop) {
		this.showCop = showCop;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getValue() {
		return id;
	}
	public String getLabel() {
		return title;
	}
	
	
	
	public ItemMenu() {
		super();
	}
	public ItemMenu(int id, String title, String icon, int showCop, int order) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
		this.showCop = showCop;
		this.order = order;
		this.value = id;
		this.label = title;
	}
	
	
	

}

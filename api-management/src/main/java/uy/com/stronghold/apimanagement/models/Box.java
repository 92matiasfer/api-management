package uy.com.stronghold.apimanagement.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "box")
@Table(name = "box", schema = "juncal_management")
public class Box {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_calculation_method")
	private CalculationMethod calculationMethod;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "id_building")
	private Building building;
	@OneToMany(mappedBy = "box")
	private List<BoxSettlementMonth> boxesSettlementMonths;

	@JsonIgnore
	@OneToMany(mappedBy = "box")
    private Set<NestedBox> nestedBoxes;
	@JsonIgnore
    @OneToMany(mappedBy = "box")
    private Set<NestedBoxSupplier> nestedSupplierBoxes;
	
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

	public CalculationMethod getCalculationMethod() {
		return calculationMethod;
	}
	public void setCalculationMethod(CalculationMethod calculationMethod) {
		this.calculationMethod = calculationMethod;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public int getValue() {
		return id;
	}
	public String getLabel() {
		return name;
	}
	public List<BoxSettlementMonth> getBoxesSettlementMonths() {
		return boxesSettlementMonths;
	}
	public void setBoxesSettlementMonths(List<BoxSettlementMonth> boxesSettlementMonths) {
		this.boxesSettlementMonths = boxesSettlementMonths;
	}
	public Set<NestedBox> getNestedBoxes() {
		return nestedBoxes;
	}
	public void setNestedBoxes(Set<NestedBox> nestedBoxes) {
		this.nestedBoxes = nestedBoxes;
	}
	public Set<NestedBoxSupplier> getNestedSupplierBoxes() {
		return nestedSupplierBoxes;
	}
	public void setNestedSupplierBoxes(Set<NestedBoxSupplier> nestedSupplierBoxes) {
		this.nestedSupplierBoxes = nestedSupplierBoxes;
	}
	public Box() {
		super();
	}
	
	public Box(int id, String name, CalculationMethod calculationMethod, Building building, 
			List<BoxSettlementMonth> boxesSettlementMonths, Set<NestedBox> nestedBoxes, Set<NestedBoxSupplier> nestedSupplierBoxes) {
		super();
		this.id = id;
		this.name = name;
		this.calculationMethod = calculationMethod;
		this.building = building;
		this.boxesSettlementMonths = boxesSettlementMonths;
		this.nestedBoxes = nestedBoxes;
		this.nestedSupplierBoxes = nestedSupplierBoxes;
	}
	 
}

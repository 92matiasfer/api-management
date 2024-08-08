package uy.com.stronghold.apimanagement.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "nested_box_supplier")
@Table(name = "nested_box_supplier", schema = "juncal_management")
public class NestedBoxSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;
    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier; 
    @ManyToOne
    @JoinColumn(name = "id_box")
    private Box box;
    @ManyToOne
    @JoinColumn(name = "id_nested_box")
    private Box nestedBox;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public BigDecimal getPercentage() {
        return percentage;
    }
    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
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
    public Box getNestedBox() {
        return nestedBox;
    }
    public void setNestedBox(Box nestedBox) {
        this.nestedBox = nestedBox;
    }

    public NestedBoxSupplier() {
        super();
    }

    public NestedBoxSupplier(int id, BigDecimal percentage, Supplier supplier, Box box, Box nestedBox) {
        super();
        this.id = id;
        this.percentage = percentage;
        this.supplier = supplier;
        this.box = box;
        this.nestedBox = nestedBox;
    }
    
}

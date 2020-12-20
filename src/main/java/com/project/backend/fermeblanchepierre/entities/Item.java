package com.project.backend.fermeblanchepierre.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Items")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idItem",
        scope = Item.class
)
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idItem", nullable = false)
    private Integer idItem;

    @Column(name = "label")
    private String label;

    @Column(name = "price")
    private Double price;

    @Column(name = "descr")
    private String descr;

    @Column(name = "qt")
    private Double qt;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idMeasure")
    private Measure measure;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "ItemContains",
            joinColumns = @JoinColumn(name = "idItem"),
            inverseJoinColumns = @JoinColumn(name = "idAllergen"))
    private List<Allergen> allergens;

    public Integer getIdItem() {
        return idItem;
    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getQt() {
        return qt;
    }

    public void setQt(Double qt) {
        this.qt = qt;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}

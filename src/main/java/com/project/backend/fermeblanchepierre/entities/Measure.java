package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Measures")
public class Measure {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idMeasure", nullable = false)
    private Integer idMeasure;

    @Column(name = "label")
    private String label;

    @Column(name = "unit")
    private String unit;

    @OneToMany(mappedBy = "measure", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Item> items;

    public Integer getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(Integer idMeasure) {
        this.idMeasure = idMeasure;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonIgnore
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}

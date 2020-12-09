package com.project.backend.fermeblanchepierre.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Allergens")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAllergen", nullable = false)
    private Integer idAllergen;

    @Column(name = "label")
    private String label;

    @ManyToMany(mappedBy = "allergens", fetch = FetchType.LAZY)
    private List<Item> items;

    public Integer getIdAllergen() {
        return idAllergen;
    }
    public void setIdAllergen(Integer idAllergen) {
        this.idAllergen = idAllergen;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonIgnore
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

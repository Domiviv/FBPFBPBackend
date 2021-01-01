package com.project.backend.fermeblanchepierre.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "ItemContains")
@IdClass(ItemContains.class)
public class ItemContains implements Serializable {

    @Id
    @JoinColumn(name = "idAllergen", nullable = false)
    private Integer idAllergen;

    @Id
    @JoinColumn(name = "idItem", nullable = false)
    private Integer idItem;

    // GETTERS & SETTERS

    public Integer getIdAllergen() {
        return idAllergen;
    }

    public void setIdAllergen(Integer idAllergen) {
        this.idAllergen = idAllergen;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

}

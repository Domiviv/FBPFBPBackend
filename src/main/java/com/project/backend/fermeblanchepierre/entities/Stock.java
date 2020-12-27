package com.project.backend.fermeblanchepierre.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idStock", nullable = false)
    private Integer idStock;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idItem")
    private Item item;


    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}

package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "SoldItems")
@JsonIgnoreProperties({"order"})
public class SoldItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idSoldItem", nullable = false)
    private Integer idSoldItem;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "idItem")
    private Item item;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idOrder")
    private Order order;

    public Integer getIdSoldItem() {
        return idSoldItem;
    }

    public void setIdSoldItem(Integer idSoldItem) {
        this.idSoldItem = idSoldItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

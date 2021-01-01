package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "OrderStatus")
@JsonIgnoreProperties({"order"})
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatus", nullable = false)
    private Integer idStatus;

    @Column(name = "label")
    private String label;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Order> orders;


    // GETTERS & SETTERS

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("order")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

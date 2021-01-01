package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idOrder",
        scope = Order.class
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idOrder", nullable = false)
    private Integer idOrder;

    @Column(name = "orderDt")
    private LocalDateTime orderDt;

    @Column(name = "pickupDt")
    private LocalDateTime pickupDt;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idStatus")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<SoldItem> soldItems;


    // GETTERS & SETTERS

    public List<SoldItem> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(LocalDateTime orderDt) {
        this.orderDt = orderDt;
    }

    public LocalDateTime getPickupDt() {
        return pickupDt;
    }

    public void setPickupDt(LocalDateTime pickupDt) {
        this.pickupDt = pickupDt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

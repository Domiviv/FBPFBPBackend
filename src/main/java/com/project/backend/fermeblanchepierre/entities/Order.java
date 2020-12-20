package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idOrder",
        scope = Order.class
)
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinColumn(name = "idOrder", nullable = false)
    private Integer idOrder;

    @Column(name = "orderDt")
    private Date orderDt;

    @Column(name = "pickupDt")
    private Date pickupDt;

    @Column(name = "billNumber")
    private String billNumber;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idStatus")
    private OrderStatus status;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }

    public Date getPickupDt() {
        return pickupDt;
    }

    public void setPickupDt(Date pickupDt) {
        this.pickupDt = pickupDt;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
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

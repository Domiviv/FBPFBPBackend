package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Order;
import com.project.backend.fermeblanchepierre.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository oR;

    public Set<Order> getOrders() {
        return new HashSet<>((Collection<Order>) oR.findAll());
    }

    public Order save(Order order){ return oR.save(order); }

    public Order getOrderById(Integer id){ return oR.findById(id).orElse(null); }

    public List<Order> getOrdersByUserEmail(String email){ return oR.getOrdersByUserEmail(email); }

    @Transactional
    public String cancelOrder(Integer id){
        try {
            oR.cancelOrder(id);
            return "Order canceled";
        }catch (EmptyResultDataAccessException e){
            return "The order you want to delete does not exist";
        }
    }

    public Boolean updateOrderById(Integer id, Order newOrder) {
        Order order = oR.findById(id).orElse(null);
        if (order != null) {
            order.setIdOrder(id);
            order.setOrderDt(newOrder.getOrderDt());
            order.setPickupDt(newOrder.getPickupDt());
            order.setStatus(newOrder.getStatus());
            order.setUser(newOrder.getUser());
            oR.save(order);
            return true;
        }
        return false;
    }
}

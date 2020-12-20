package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Order;
import com.project.backend.fermeblanchepierre.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderSI;

    @GetMapping("/all")
    private Set<Order> getOrders(){ return orderSI.getOrders(); }

    @PostMapping("/add")
    private Order addOrder(@RequestBody Order order) { return orderSI.save(order); }

    @GetMapping("/{id}")
    private Order getOrderById(@PathVariable Integer id) { return orderSI.getOrderById(id); }

    @PutMapping("/cancel/{id}")
    private String cancelOrder(@PathVariable Integer id) { return orderSI.cancelOrder(id); }

    @PutMapping("/update/{id}")
    private Boolean updateOrderById(@PathVariable Integer id,@RequestBody Order order) { return orderSI.updateOrderById(id,order); }


}

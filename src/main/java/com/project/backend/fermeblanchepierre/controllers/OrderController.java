package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Order;
import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.services.EmailServiceImpl;
import com.project.backend.fermeblanchepierre.services.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderSI;

    @Autowired
    private EmailServiceImpl emailSI;

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

    @PostMapping("/mail")
    private void confirmOrder(@RequestBody User user) {

        System.out.println(user);

        String email = user.getEmail();
        System.out.println(email);
        try {
            emailSI.confirmOrder(email);
        }catch (MailException e){
            logger.info("Error sending mail : " + e.getMessage());
        }


    }
}

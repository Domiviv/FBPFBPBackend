package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Order;
import com.project.backend.fermeblanchepierre.entities.Stock;
import com.project.backend.fermeblanchepierre.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @Autowired
    private StockServiceImpl stockSI;

    @Autowired
    private SoldItemServiceImpl soldItemSI;

    @Autowired
    private UserServiceImpl userSI;

    @Autowired
    private OrderStatusServiceImpl orderStatusSI;

    @GetMapping("/all")
    private Set<Order> getOrders(){ return orderSI.getOrders(); }

    @PostMapping("/add")
    private Boolean addOrder(@RequestParam Integer idUser, @RequestBody List<Stock> stocks) {

        // CHECK IF IN STOCK
        for (Stock stock : stocks) {
            if (stockSI.getFirstStockByItemId(stock.getItem().getIdItem()) == null) { return false; }
        }

        // CREATE ORDER
        Order order = new Order();
        order.setOrderDt(LocalDateTime.now());
        order.setUser(userSI.getUserById(idUser));
        order.setStatus(orderStatusSI.getOrderStatusById(1));
        Order newOrder = orderSI.save(order);

        // REMOVE FROM STOCK AND CREATE SOLDITEM
        for (Stock stock : stocks) {
            stockSI.deleteStockByItemId(stock.getItem().getIdItem());
            soldItemSI.addSoldItem(stock.getItem().getIdItem(), newOrder.getIdOrder());
        }

        // SENDING MAIL
        String email = order.getUser().getEmail();
        try {
            emailSI.confirmOrder(email, order, stocks);
        }catch (MailException e){
            logger.info("Error sending mail : " + e.getMessage());
        }
        return true;
    }

    @GetMapping("/{id}")
    private Order getOrderById(@PathVariable Integer id) { return orderSI.getOrderById(id); }

    @GetMapping("/user")
    private List<Order> getOrdersByUserEmail(@RequestParam String email) { return orderSI.getOrdersByUserEmail(email); }

    @PutMapping("/cancel/{id}")
    private String cancelOrder(@PathVariable Integer id) { return orderSI.cancelOrder(id); }

    @PutMapping("/update/{id}")
    private Boolean updateOrderById(@PathVariable Integer id,@RequestBody Order order) { return orderSI.updateOrderById(id,order); }
}

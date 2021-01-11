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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/order")
public class OrderController {

    // Logger pour la gestion d'erreur avec l'e-mail de confirmation
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    // Déclaration des services appelés
    @Autowired
    private OrderServiceImpl oSI;

    @Autowired
    private EmailServiceImpl eSI;

    @Autowired
    private StockServiceImpl sSI;

    @Autowired
    private SoldItemServiceImpl sISI;

    @Autowired
    private UserServiceImpl uSI;

    @Autowired
    private OrderStatusServiceImpl oSSI;

    // GET MAPPINGS

    @GetMapping("/all")
    private List<Order> getAllOrders() {
        return oSI.getAllOrders();
    }

    @GetMapping("/{id}")
    private Order getOrderById(@PathVariable Integer id) {
        return oSI.getOrderById(id);
    }

    @GetMapping("/user")
    private List<Order> getOrdersByUserEmail(@RequestParam String email) {
        return oSI.getOrdersByUserEmail(email);
    }


    // POST MAPPINGS

    @PostMapping("/add")
    private Boolean addOrder(@RequestParam Integer idUser, @RequestBody List<Stock> stocks) {


        // CHECK IF IN STOCK
        for (Stock stock : stocks) {
            if (sSI.getFirstStockByItemId(stock.getItem().getIdItem()) == null) {
                return false;
            }
        }

        // CREATE ORDER
        Order order = new Order();
        order.setOrderDt(LocalDateTime.now());
        order.setUser(uSI.getUserById(idUser));
        order.setStatus(oSSI.getOrderStatusById(1));
        Order newOrder = oSI.addOrder(order);

        // REMOVE FROM STOCK AND CREATE SOLDITEM
        for (Stock stock : stocks) {
            sSI.deleteStockByItemId(stock.getItem().getIdItem());
            sISI.addSoldItem(stock.getItem().getIdItem(), newOrder.getIdOrder());
        }

        // SENDING MAIL
        String email = order.getUser().getEmail();
        try {
            eSI.confirmOrder(email, order, stocks);
        } catch (MailException e) {
            logger.info("Error sending mail : " + e.getMessage());
        }
        return true;
    }

    // PUT MAPPINGS

    // Annule une commande sur base de son id
    @PutMapping("/cancel/{id}")
    private String cancelOrder(@PathVariable Integer id) {
        return oSI.cancelOrder(id);
    }

    // Confirme le paiement d'une commande sur base de son id
    @PutMapping("/confirm-payment/{id}")
    private String confirmPayment(@PathVariable Integer id) {
        return oSI.confirmPayment(id);
    }

    // Confirme l'enlèvement d'une commande sur base de son id
    @PutMapping("/confirm-receipt/{id}")
    private String confirmReceipt(@PathVariable Integer id) {
        return oSI.confirmReceipt(id);
    }

    // modifie une commande sur base de son id
    @PutMapping("/update/{id}")
    private Boolean updateOrderById(@PathVariable Integer id, @RequestBody Order order) {
        return oSI.updateOrderById(id, order);
    }
}

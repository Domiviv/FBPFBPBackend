package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.OrderStatus;
import com.project.backend.fermeblanchepierre.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    OrderStatusRepository oSR;


    // GET

    public OrderStatus getOrderStatusById(Integer id) {
        return oSR.findById(id).orElse(null);
    }
}

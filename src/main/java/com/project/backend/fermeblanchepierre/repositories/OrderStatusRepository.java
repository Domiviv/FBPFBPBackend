package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {
}

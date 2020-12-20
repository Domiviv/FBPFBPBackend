package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.Order;
import com.project.backend.fermeblanchepierre.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Modifying
    @Query(value = "UPDATE orders SET idStatus = 6 WHERE idOrder = :id", nativeQuery = true)
    void cancelOrder(@Param("id") Integer id);

}

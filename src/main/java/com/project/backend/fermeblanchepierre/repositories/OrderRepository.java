package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Modifying
    @Query(value = "UPDATE orders SET idStatus = 6 WHERE idOrder = :id", nativeQuery = true)
    void cancelOrder(@Param("id") Integer id);

    @Query(value = "SELECT o.* FROM users u JOIN orders o ON u.idUser = o.idUser WHERE u.email = :email", nativeQuery = true)
    List<Order> getOrdersByUserEmail(@Param("email") String email);

}

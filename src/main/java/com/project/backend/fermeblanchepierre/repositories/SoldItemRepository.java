package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.SoldItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SoldItemRepository extends CrudRepository<SoldItem, Integer> {

    @Modifying
    @Query(value = "INSERT INTO SoldItems (idItem, idOrder) VALUES (:idItem,:idOrder)", nativeQuery = true)
    void addSoldItem(@Param("idItem") Integer idItem, @Param("idOrder") Integer idOrder);
}

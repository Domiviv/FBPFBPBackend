package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {

    @Query(value = "SELECT COUNT(idItem) FROM Stocks WHERE idItem = :id", nativeQuery = true)
    Integer getStockCountById(@Param("id") Integer id);


    @Query(value = "SELECT * FROM Stocks WHERE idItem = :idItem LIMIT 1", nativeQuery = true)
    Stock getFirstStockByItemId(@Param("idItem") Integer idItem);

    @Modifying
    @Query(value = "DELETE FROM Stocks WHERE idItem = :idItem LIMIT 1", nativeQuery = true)
    void deleteStockByItemId(@Param("idItem") Integer idItem)
    ;


}

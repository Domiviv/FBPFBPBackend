package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.ItemContains;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemContainsRepository extends CrudRepository<ItemContains, Integer> {

    @Modifying
    @Query(value = "DELETE FROM ItemContains WHERE idItem = :id", nativeQuery = true)
    void deleteByItemId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM ItemContains WHERE idItem = :id", nativeQuery = true)
    List<ItemContains> getAllByItemId(@Param("id") Integer id);
}

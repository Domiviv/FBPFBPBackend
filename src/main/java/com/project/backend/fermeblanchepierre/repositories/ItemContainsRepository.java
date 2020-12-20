package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.ItemContains;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemContainsRepository extends CrudRepository<ItemContains, Integer> {

    @Modifying
    @Query(value = "DELETE FROM ItemContains WHERE idItem = :id", nativeQuery = true)
    void deleteByItemId(@Param("id") Integer id);

    @Query(value = "select * from ItemContains where idItem = :id", nativeQuery = true)
    Set<ItemContains> getAllByItemId(@Param("id") Integer id);
}

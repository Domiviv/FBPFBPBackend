package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

        @Query(value = "select * from users where idRole = 1", nativeQuery = true)
        Set<User> getAdministrators();

        @Query(value = "select * from users where idRole = 2", nativeQuery = true)
        Set<User> getCustomers();

        Optional<User> findByEmail(String email);

}

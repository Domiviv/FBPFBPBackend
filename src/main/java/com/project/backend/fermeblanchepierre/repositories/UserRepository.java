package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

        @Query(value = "SELECT * FROM users WHERE idRole = 1", nativeQuery = true)
        List<User> getAllAdministrators();

        @Query(value = "SELECT * FROM users WHERE idRole = 2", nativeQuery = true)
        List<User> getAllCustomers();

        Optional<User> findByEmail(String email);
}

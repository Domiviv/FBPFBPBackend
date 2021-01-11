package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public class UserController {

    // Déclaration du service
    @Autowired
    private UserServiceImpl uSI;


    // GET MAPPINGS

    // Récupère la liste des utilisateurs
    @GetMapping("/all")
    private List<User> getAllUsers() {
        return uSI.getAllUsers();
    }

    // Récupère un utilisateur sur base de son id
    @GetMapping("/{id}")
    private User getUserById(@PathVariable Integer id) {
        return uSI.getUserById(id);
    }

    // Récupère un utilisateur sur base de son e-mail
    @GetMapping("/connected")
    private User getUserByEmail(@RequestParam String email) {
        return uSI.getUserByEmail(email);
    }


    // Récupère la liste des administrateurs
    @GetMapping("/administrators")
    private List<User> getAllAdministrators() {
        return uSI.getAllAdministrators();
    }

    // Récupère la liste des clients
    @GetMapping("/customers")
    private List<User> getAllCustomers() {
        return uSI.getAllCustomers();
    }


    // POST MAPPINGS

    // Ajoute un utilisateur
    @PostMapping("/add")
    private Boolean addUser(@RequestBody User user) {
        return uSI.addUser(user);
    }

    // Ajoute un client
    @PostMapping("/customer/add")
    private Boolean addCustomer(@RequestBody User user) {
        return uSI.addCustomer(user);
    }

    // Ajoute une liste d'utilisateurs
    @PostMapping("/addList")
    private List<User> addList(@RequestBody List<User> userList) {
        return uSI.addUserList(userList);
    }


    // DELETE MAPPINGS

    // Supprime un utilisateur sur base de son id
    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id) {
        return uSI.deleteUserById(id);
    }

    // Vide la database des utilisateurs
    @DeleteMapping("/delete/all")
    private String deleteAllUsers() {
        return uSI.deleteAllUsers();
    }


    // PUT MAPPINGS

    // Modifie un utilisateur sur base de son id
    @PutMapping("/update/{id}")
    private Boolean updateUserById(@PathVariable Integer id, @RequestBody User newUser) {
        return uSI.updateUserById(id, newUser);
    }
}

package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserServiceImpl userSI;

    @GetMapping("/all")
    private Set<User> getUsers(){
        return userSI.getUsers();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Integer id){
        return userSI.getUserById(id);
    }

    @GetMapping("/connected")
    private User getUserByEmail(@RequestParam String email){
        return userSI.getUserByEmail(email);
    }


    @GetMapping("/administrators")
    private Set<User> getAdministrators() { return userSI.getAdministrators(); }

    @GetMapping("/customers")
    private Set<User> getCustomers() { return userSI.getCustomers(); }

    @PostMapping("/add")
    private User add(@RequestBody User user) {
        return userSI.save(user);
    }

    @PostMapping("/customer/add")
    private User addCustomer(@RequestBody User user) {
        return userSI.saveCustomer(user);
    }

    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id){
        return userSI.deleteUserById(id);
    }

    @DeleteMapping("/delete/all")
    private String deleteAllUsers(){
        return userSI.deleteAllUsers();
    }

    @PutMapping("/update/{id}")
    private Boolean updateUserById(@PathVariable Integer id, @RequestBody User newUser){
        return userSI.updateUserById(id, newUser);
    }

    @PostMapping("/addList")
    private Iterable<User> addList(@RequestBody Set<User> userList){
        return userSI.saveAll(userList);
    }
}

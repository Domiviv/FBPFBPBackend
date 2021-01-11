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

    @Autowired
    private UserServiceImpl uSI;


    // GET MAPPINGS

    @GetMapping("/all")
    private List<User> getUsers() {
        return uSI.getAllUsers();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Integer id) {
        return uSI.getUserById(id);
    }

    @GetMapping("/connected")
    private User getUserByEmail(@RequestParam String email) {
        return uSI.getUserByEmail(email);
    }


    @GetMapping("/administrators")
    private List<User> getAllAdministrators() {
        return uSI.getAllAdministrators();
    }

    @GetMapping("/customers")
    private List<User> getAllCustomers() {
        return uSI.getAllCustomers();
    }


    // POST MAPPINGS

    @PostMapping("/add")
    private Boolean addUser(@RequestBody User user) {
        return uSI.addUser(user);
    }

    @PostMapping("/customer/add")
    private Boolean addCustomer(@RequestBody User user) {
        return uSI.addCustomer(user);
    }

    @PostMapping("/addList")
    private List<User> addList(@RequestBody List<User> userList) {
        return uSI.addUserList(userList);
    }


    // DELETE MAPPINGS

    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id) {
        return uSI.deleteUserById(id);
    }

    @DeleteMapping("/delete/all")
    private String deleteAllUsers() {
        return uSI.deleteAllUsers();
    }


    // PUT MAPPINGS

    @PutMapping("/update/{id}")
    private Boolean updateUserById(@PathVariable Integer id, @RequestBody User newUser) {
        return uSI.updateUserById(id, newUser);
    }
}

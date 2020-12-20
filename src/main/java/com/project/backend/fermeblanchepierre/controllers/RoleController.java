package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Role;
import com.project.backend.fermeblanchepierre.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(path="/all")
    public Iterable<Role> getAllRoles() {
        // This returns a JSON or XML with the users
        return roleRepository.findAll();
    }

}

package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Role;
import com.project.backend.fermeblanchepierre.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {

    @Autowired
    private RoleServiceImpl rSI;


    // GET MAPPINGS

    @GetMapping(path = "/all")
    public List<Role> getAllRoles() {
        return rSI.getAllRoles();
    }

}

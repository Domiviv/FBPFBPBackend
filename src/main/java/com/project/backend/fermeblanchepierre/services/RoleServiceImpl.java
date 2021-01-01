package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Role;
import com.project.backend.fermeblanchepierre.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository rR;

    // GET

    public List<Role> getAllRoles() {
        return (List<Role>) rR.findAll();
    }
}

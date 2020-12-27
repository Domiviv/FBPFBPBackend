package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Item;
import com.project.backend.fermeblanchepierre.entities.SoldItem;
import com.project.backend.fermeblanchepierre.services.SoldItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/sold-item")
public class SoldItemController {

    @Autowired
    private SoldItemServiceImpl sISI;

    // GET MAPPINGS
    @GetMapping("/all")
    private List<SoldItem> getAllSoldItems() { return sISI.getAllSoldItems(); }


    // POST MAPPINGS
    @PostMapping("/add")
    private void addSoldItem(@RequestParam Integer idItem, @RequestParam Integer idOrder) {
        System.out.println("CONTROLLER OK");
        sISI.addSoldItem(idItem, idOrder);
    }


}

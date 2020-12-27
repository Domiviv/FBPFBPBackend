package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.ItemContains;
import com.project.backend.fermeblanchepierre.services.ItemContainsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/item-contains")
public class ItemContainsController {

    @Autowired
    private ItemContainsServiceImpl iCSI;


    // GET MAPPINGS

    @GetMapping("/all")
    private List<ItemContains> getAllItemContains() {
        return iCSI.getAllItemContains();
    }

    @GetMapping("/{id}")
    private List<ItemContains> getItemContainsById(@PathVariable Integer id) {
        return iCSI.getItemContainsById(id);
    }


    // POST MAPPINGS

    @PostMapping("/add")
    private ItemContains addItemContains(@RequestBody ItemContains itemContains) {
        return iCSI.addItemContains(itemContains);
    }


    // DELETE MAPPINGS

    @DeleteMapping("/delete")
    private String deleteItemContains(@RequestBody ItemContains itemContains) {
        return iCSI.deleteItemContains(itemContains);
    }

    @DeleteMapping("/delete/{id}")
    private String deleteItemContainsByItemId(@PathVariable Integer id) {
        return iCSI.deleteItemContainsById(id);
    }


    //PUT MAPPINGS

    @PutMapping("/update/{id}")
    private Boolean updateItemContainsById(@PathVariable Integer id, @RequestBody ItemContains itemContains) {
        return iCSI.updateItemContainsById(id, itemContains);
    }
}

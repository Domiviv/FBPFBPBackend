package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Item;
import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.services.ItemServiceImpl;
import com.project.backend.fermeblanchepierre.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/item")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemSI;

    @GetMapping("/all")
    private Set<Item> getItems(){ return itemSI.getItems(); }

    @GetMapping("/{id}")
    private Item getItemById(@PathVariable Integer id) { return  itemSI.getItemById(id); }

    @PostMapping("/add")
    private Item add(@RequestBody Item item) { return itemSI.save(item); }

    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id) { return itemSI.deleteItemById(id); }


    @DeleteMapping("/delete/all")
    private String deleteAllItems(){ return itemSI.deleteAllItems(); }

    @PutMapping("/update/{id}")
    private Item updateItemById(@PathVariable Integer id, @RequestBody Item newItem){
        return itemSI.updateItemById(id, newItem);
    }

    @PostMapping("/addList")
    private Iterable<Item> addList(@RequestBody Set<Item> itemList){
        return itemSI.saveAll(itemList);
    }

}


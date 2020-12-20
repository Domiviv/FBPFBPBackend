package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.ItemContains;
import com.project.backend.fermeblanchepierre.services.ItemContainsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/item-contains")
public class ItemContainsController {

    @Autowired
    private ItemContainsServiceImpl itemContainsSI;

    @GetMapping("/all")
    private Set<ItemContains> getItemContains(){ return itemContainsSI.getItemContains(); }

    @GetMapping("/{id}")
    private Set<ItemContains> getItemContainsById(@PathVariable Integer id) { return  itemContainsSI.getItemContainsById(id); }

    @PostMapping("/add")
    private ItemContains add(@RequestBody ItemContains itemContains) { return itemContainsSI.save(itemContains); }

    @DeleteMapping("/delete")
    private String delete(@RequestBody ItemContains itemContains) { return itemContainsSI.delete(itemContains); }

    @DeleteMapping("/delete/{id}")
    private String deleteByItemId(@PathVariable Integer id) { return itemContainsSI.deleteByItemId(id); }
}

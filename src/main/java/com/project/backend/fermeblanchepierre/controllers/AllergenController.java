package com.project.backend.fermeblanchepierre.controllers;


import com.project.backend.fermeblanchepierre.entities.Allergen;
import com.project.backend.fermeblanchepierre.services.AllergenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/allergen")
public class AllergenController {


    @Autowired
    private AllergenServiceImpl allergenSI;

    @GetMapping("/all")
    private Set<Allergen> getAllergens(){ return allergenSI.getAllergens(); }

    @GetMapping("/{id}")
    private Allergen getAllergenById(@PathVariable Integer id) { return  allergenSI.getAllergenById(id); }

    @PostMapping("/add")
    private Allergen add(@RequestBody Allergen allergen) { return allergenSI.save(allergen); }

    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id) { return allergenSI.deleteAllergenById(id); }


    @DeleteMapping("/delete/all")
    private String deleteAllAllergens(){ return allergenSI.deleteAllAllergens(); }

    @PutMapping("/update/{id}")
    private Allergen updateAllergenById(@PathVariable Integer id, @RequestBody Allergen newAllergen){
        return allergenSI.updateAllergenById(id, newAllergen);
    }

    @PostMapping("/addList")
    private Iterable<Allergen> addList(@RequestBody Set<Allergen> allergenList){
        return allergenSI.saveAll(allergenList);
    }


}

package com.project.backend.fermeblanchepierre.controllers;


import com.project.backend.fermeblanchepierre.entities.Allergen;
import com.project.backend.fermeblanchepierre.services.AllergenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/allergen")
public class AllergenController {

    @Autowired
    private AllergenServiceImpl aSI;

    // GET MAPPINGS

    @GetMapping("/all")
    private List<Allergen> getAllAllergens() {
        return aSI.getAllAllergens();
    }

    @GetMapping("/{id}")
    private Allergen getAllergenById(@PathVariable Integer id) {
        return aSI.getAllergenById(id);
    }


    // POST MAPPINGS

    @PostMapping("/add")
    private Allergen addAllergen(@RequestBody Allergen allergen) {
        return aSI.addAllergen(allergen);
    }

    @PostMapping("/add-list")
    private List<Allergen> addAllergenList(@RequestBody List<Allergen> allergenList) {
        return aSI.addAllergenList(allergenList);
    }

    // DELETE MAPPINGS

    @DeleteMapping("/delete/{id}")
    private String deleteAllergenById(@PathVariable Integer id) {
        return aSI.deleteAllergenById(id);
    }

    @DeleteMapping("/delete/all")
    private String deleteAllAllergens() {
        return aSI.deleteAllAllergens();
    }


    //PUT MAPPINGS

    @PutMapping("/update/{id}")
    private Allergen updateAllergenById(@PathVariable Integer id, @RequestBody Allergen newAllergen) {
        return aSI.updateAllergenById(id, newAllergen);
    }


}

package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Stock;
import com.project.backend.fermeblanchepierre.services.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/stock")
public class StockController {

    // Déclaration du service
    @Autowired
    private StockServiceImpl sSI;

    // GET MAPPINGS

    // Récupère le nombre d'occurence d'un produit en stock sur base de l'id produit
    @GetMapping("/count/{id}")
    private Integer getStockCountById(@PathVariable Integer id) { return sSI.getStockCountById(id); }

    // Vérifie si un produit existe dans le stock
    @GetMapping("/item/{idItem}")
    private Stock getFirstStockByItemId(@PathVariable Integer idItem) { return sSI.getFirstStockByItemId(idItem); }


    // POST MAPPINGS

    // Peuple la database de x occurence
    @PostMapping("/populate/{x}")
    public void populate(@PathVariable Integer x) {
        sSI.populate(x);
    }
}

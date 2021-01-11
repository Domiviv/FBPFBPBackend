package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Stock;
import com.project.backend.fermeblanchepierre.services.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/stock")
public class StockController {

    @Autowired
    private StockServiceImpl sSI;

    // GET MAPPINGS

    @GetMapping("/count/{id}")
    private Integer getStockCountById(@PathVariable Integer id) { return sSI.getStockCountById(id); }

    @GetMapping("/item/{idItem}")
    private Stock getFirstStockByItemId(@PathVariable Integer idItem) { return sSI.getFirstStockByItemId(idItem); }


    // POST MAPPINGS
    @PostMapping("/populate/{x}")
    public void populate(@PathVariable Integer x) {
        sSI.populate(x);
    }
}

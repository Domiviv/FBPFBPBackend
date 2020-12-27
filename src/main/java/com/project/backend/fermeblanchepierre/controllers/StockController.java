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
    private StockServiceImpl stockSI;

    // GET MAPPINGS

    @GetMapping("/count/{id}")
    private Integer getStockCountById(@PathVariable Integer id) { return stockSI.getStockCountById(id); }

    @GetMapping("/item/{idItem}")
    private Stock getFirstStockByItemId(@PathVariable Integer idItem) { return stockSI.getFirstStockByItemId(idItem); }

}

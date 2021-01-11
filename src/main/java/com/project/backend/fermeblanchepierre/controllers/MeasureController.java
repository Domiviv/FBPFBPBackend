package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Measure;
import com.project.backend.fermeblanchepierre.services.MeasureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/measure")
public class MeasureController {

    // Déclaration du service
    @Autowired
    private MeasureServiceImpl mSI;

    // GET MAPPINGS

    @GetMapping("/all")
    private List<Measure> getAllMeasures(){ return mSI.getAllMeasures(); }

    @GetMapping("/{id}")
    private Measure getMeasureById(@PathVariable Integer id) { return  mSI.getMeasureById(id); }


    // POST MAPPINGS

    @PostMapping("/add")
    private Measure addMeasure(@RequestBody Measure measure) { return mSI.addMeasure(measure); }

    @PostMapping("/addList")
    private List<Measure> addMeasureList(@RequestBody List<Measure> measureList){
        return mSI.addMeasureList(measureList);
    }

    // DELETE MAPPINGS
    @DeleteMapping("/delete/{id}")
    private String deleteMeasureById(@PathVariable Integer id) { return mSI.deleteMeasureById(id); }
    
    @DeleteMapping("/delete/all")
    private String deleteAllMeasures(){ return mSI.deleteAllMeasures(); }


    // PUT MAPPINGS
    @PutMapping("/update/{id}")
    private Measure updateMeasureById(@PathVariable Integer id, @RequestBody Measure measure){
        return mSI.updateMeasureById(id, measure);
    }
}

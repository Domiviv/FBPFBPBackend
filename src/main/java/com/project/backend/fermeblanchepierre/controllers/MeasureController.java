package com.project.backend.fermeblanchepierre.controllers;

import com.project.backend.fermeblanchepierre.entities.Measure;
import com.project.backend.fermeblanchepierre.services.MeasureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/measure")
public class MeasureController {

    @Autowired
    private MeasureServiceImpl measureSI;

    @GetMapping("/all")
    private Set<Measure> getMeasures(){ return measureSI.getMeasures(); }

    @GetMapping("/{id}")
    private Measure getMeasureById(@PathVariable Integer id) { return  measureSI.getMeasureById(id); }

    @PostMapping("/add")
    private Measure add(@RequestBody Measure measure) { return measureSI.save(measure); }

    @DeleteMapping("/delete/{id}")
    private String deleteUserById(@PathVariable Integer id) { return measureSI.deleteMeasureById(id); }


    @DeleteMapping("/delete/all")
    private String deleteAllMeasures(){ return measureSI.deleteAllMeasures(); }

    @PutMapping("/update/{id}")
    private Measure updateMeasureById(@PathVariable Integer id, @RequestBody Measure newMeasure){
        return measureSI.updateMeasureById(id, newMeasure);
    }

    @PostMapping("/addList")
    private Iterable<Measure> addList(@RequestBody Set<Measure> measureList){
        return measureSI.saveAll(measureList);
    }

}

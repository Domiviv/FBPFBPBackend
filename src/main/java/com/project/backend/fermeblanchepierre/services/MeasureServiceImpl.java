package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Measure;
import com.project.backend.fermeblanchepierre.repositories.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureServiceImpl implements MeasureService {

    @Autowired
    private MeasureRepository mR;

    public List<Measure> getAllMeasures() {
        return (List<Measure>) mR.findAll();
    }

    public Measure getMeasureById(Integer id){
        return mR.findById(id).orElse(null);
    }

    public String deleteMeasureById(Integer id){
        try {
            mR.deleteById(id);
            return "Measure removed";
        }catch (EmptyResultDataAccessException e){
            return "The measure you want to delete does not exist";
        }
    }

    public String deleteAllMeasures() {
        try {
            mR.deleteAll();
            return "Measures table is cleared";
        }catch (EmptyResultDataAccessException e){
            return "Measures clear function failed";
        }
    }

    public Measure addMeasure(Measure measure) { return mR.save(measure); }

    public Measure updateMeasureById(Integer id, Measure newMeasure) {
        Measure oldMeasure = mR.findById(id).orElse(null);
        if (oldMeasure != null) {
            oldMeasure.setLabel(newMeasure.getLabel());
        }
        oldMeasure.setUnit(newMeasure.getUnit());
        return mR.save(oldMeasure);
    }

    public List<Measure> addMeasureList(List<Measure> measureList) { return (List<Measure>) mR.saveAll(measureList); }

}

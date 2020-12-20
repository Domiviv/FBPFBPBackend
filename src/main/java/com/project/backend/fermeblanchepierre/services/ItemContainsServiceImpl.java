package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.ItemContains;
import com.project.backend.fermeblanchepierre.repositories.ItemContainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ItemContainsServiceImpl {

    @Autowired
    private ItemContainsRepository iCR;

    public Set<ItemContains> getItemContains() {
        return new HashSet<>((Collection<ItemContains>) iCR.findAll());
    }

    public Set<ItemContains> getItemContainsById(Integer id){
        return iCR.getAllByItemId(id);
    }

    public String delete(ItemContains itemContains){
        try {
            iCR.delete(itemContains);
            return "ItemContains removed";
        }catch (EmptyResultDataAccessException e){
            return "The itemContains you want to delete does not exist";
        }
    }

    @Transactional
    public String deleteByItemId(Integer id){
        try {
            iCR.deleteByItemId(id);
            return "ItemContains removed";
        }catch (EmptyResultDataAccessException e){
            return "The itemContains you want to delete does not exist";
        }
    }
    public ItemContains save(ItemContains itemContains) { return iCR.save(itemContains); }

    public Boolean updateItemContainsById(Integer id, ItemContains newItemContains) {
        ItemContains oldItemContains = iCR.findById(id).orElse(null);
        if(oldItemContains != null) {
            oldItemContains.setIdAllergen(newItemContains.getIdAllergen());
            oldItemContains.setIdItem(newItemContains.getIdItem());
            iCR.save(oldItemContains);
            return true;
        }
        return false;
    }
}

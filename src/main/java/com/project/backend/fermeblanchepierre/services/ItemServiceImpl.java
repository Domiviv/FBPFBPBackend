package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Item;
import com.project.backend.fermeblanchepierre.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository iR;

    public Set<Item> getItems() {
        return new HashSet<>((Collection<Item>) iR.findAll());
    }

    public Item getItemById(Integer id){
        return iR.findById(id).orElse(null);
    }

    public String deleteItemById(Integer id){
        try {
            iR.deleteById(id);
            return "Item removed";
        }catch (EmptyResultDataAccessException e){
            return "The item you want to delete does not exist";
        }
    }

    public String deleteAllItems() {
        try {
            iR.deleteAll();
            return "Items table is cleared";
        }catch (EmptyResultDataAccessException e){
            return "Items clear function failed";
        }
    }

    public Item save(Item item) { return iR.save(item); }

    public Item updateItemById(Integer id, Item newItem) {
        Item oldItem = iR.findById(id).orElse(null);
        oldItem.setLabel(newItem.getLabel());
        oldItem.setPrice(newItem.getPrice());
        oldItem.setDescr(newItem.getDescr());
        return iR.save(oldItem);
    }

    public Iterable<Item> saveAll(Set<Item> itemList) { return iR.saveAll(itemList); }

}

package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Item;
import com.project.backend.fermeblanchepierre.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository iR;

    public List<Item> getAllItems() {
        return (List<Item>) iR.findAll();
    }

    public Item getItemById(Integer id) {
        return iR.findById(id).orElse(null);
    }

    public String deleteItemById(Integer id) {
        try {
            Item item = getItemById(id);
            if (!item.getAllergens().isEmpty()) {
                item.getAllergens().clear();
            }
            iR.deleteById(id);
            return "Item removed";
        } catch (EmptyResultDataAccessException e) {
            return "The item you want to delete does not exist";
        }
    }

    public String deleteAllItems() {
        try {
            iR.deleteAll();
            return "Items table is cleared";
        } catch (EmptyResultDataAccessException e) {
            return "Items clear function failed";
        }
    }

    public Item addItem(Item item) {
        return iR.save(item);
    }

    public Boolean updateItemById(Integer id, Item newItem) {
        Item oldItem = iR.findById(id).orElse(null);
        if (oldItem != null) {
            oldItem.setLabel(newItem.getLabel());
            oldItem.setPrice(newItem.getPrice());
            oldItem.setDescr(newItem.getDescr());
            oldItem.setQt(newItem.getQt());
            oldItem.setMeasure(newItem.getMeasure());
            oldItem.setAllergens(newItem.getAllergens());
            iR.save(oldItem);
            return true;
        }
        return false;
    }

    public List<Item> addItemList(List<Item> itemList) {
        return (List<Item>) iR.saveAll(itemList);
    }

}

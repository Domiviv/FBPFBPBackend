package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Item;
import com.project.backend.fermeblanchepierre.entities.Stock;
import com.project.backend.fermeblanchepierre.repositories.ItemRepository;
import com.project.backend.fermeblanchepierre.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository sR;

    @Autowired
    private ItemRepository iR;


    // GET

    public Integer getStockCountById(Integer id) {
        return sR.getStockCountById(id);
    }

    public Stock getFirstStockByItemId(Integer idItem) {
        return sR.getFirstStockByItemId(idItem);
    }


    // POST

    @Transactional
    public void populate(Integer x) {
        List<Item> itemList = (List<Item>) iR.findAll();
        for (Item item: itemList){
            if (item.getIdItem() != 19) {
                for (int i = 0; i < x; i++) {
                    sR.populate(item.getIdItem());
                }
                System.out.println(x + " items for : " + item.getIdItem() + " OK!");
            }
        }
        System.out.println("FINISHED");
    }


    // DELETE

    @Transactional
    public String deleteStockByItemId(Integer idItem) {
        try {
            sR.deleteStockByItemId(idItem);
            return "Item removed from stock";
        } catch (EmptyResultDataAccessException e) {
            return "There's no item like this in stock";
        }
    }


}

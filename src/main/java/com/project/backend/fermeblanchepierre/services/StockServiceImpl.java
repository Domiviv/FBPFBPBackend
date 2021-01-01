package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Stock;
import com.project.backend.fermeblanchepierre.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository sR;


    // GET

    public Integer getStockCountById(Integer id) {
        return sR.getStockCountById(id);
    }

    public Stock getFirstStockByItemId(Integer idItem) {
        return sR.getFirstStockByItemId(idItem);
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

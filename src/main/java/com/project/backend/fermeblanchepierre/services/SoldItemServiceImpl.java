package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.SoldItem;
import com.project.backend.fermeblanchepierre.repositories.SoldItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SoldItemServiceImpl implements SoldItemService {

    @Autowired
    private SoldItemRepository sICR;


    // GET

    public List<SoldItem> getAllSoldItems() {
        return (List<SoldItem>) sICR.findAll();
    }


    // POST

    @Transactional
    public void addSoldItem(Integer idItem, Integer idOrder) {
        sICR.addSoldItem(idItem, idOrder);
    }
}

package com.hei.petroliere.service;

import com.hei.petroliere.model.Price;
import com.hei.petroliere.repository.PriceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceDAO priceDAO;

    @Autowired
    public PriceService(PriceDAO priceDAO) {
        this.priceDAO = priceDAO;
    }

    public List<Price> getAllPrices() {
        return priceDAO.findAll();
    }

    public Price getPrice(Integer idPrice) {
        return priceDAO.findById(idPrice);
    }

    public void createPrice(Price newPrice) {
        priceDAO.save(newPrice);
    }

    public void deletePrice(Integer idPrice) {
        priceDAO.delete(idPrice);
    }
}

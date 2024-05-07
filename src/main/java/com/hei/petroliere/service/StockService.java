package com.hei.petroliere.service;

import com.hei.petroliere.model.Stock;
import com.hei.petroliere.repository.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockDAO stockDAO;

    @Autowired
    public StockService(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public List<Stock> getAllStation() {
        return stockDAO.findAll();
    }

    public Stock getStation(Integer idStock) {
        return stockDAO.findById(idStock);
    }

    public void createStock(Stock newStock) {
        stockDAO.save(newStock);
    }

    public void deleteStock(Integer idStock) {
        stockDAO.delete(idStock);
    }
}

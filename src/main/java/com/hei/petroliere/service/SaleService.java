package com.hei.petroliere.service;

import com.hei.petroliere.model.Sale;
import com.hei.petroliere.repository.SaleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleDAO saleDAO;

    @Autowired
    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    public List<Sale> getAllSales() {
        return saleDAO.findAll();
    }

    public Sale getSale(Integer saleId) {
        return saleDAO.findById(saleId);
    }

    public void createSale(Sale newSale) {
        saleDAO.save(newSale);
    }

    public void deleteSale(Integer saleId) {
        saleDAO.delete(saleId);
    }
}

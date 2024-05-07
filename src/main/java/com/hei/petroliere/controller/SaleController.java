package com.hei.petroliere.controller;
import com.hei.petroliere.model.Sale;
import com.hei.petroliere.repository.SaleDAO;
import com.hei.petroliere.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleDAO saleDAO;

    @Autowired
    public SaleController(SaleDAO saleDAO, SaleService saleService) {
        this.saleDAO = saleDAO;
    }
    @GetMapping
    public List<Sale> getAllSales() {
        return saleDAO.findAll();
    }
    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Integer id) {
        return saleDAO.findById(id);
    }
    @PostMapping
    public void createSale(@RequestBody Sale newSale) {
        saleDAO.save(newSale);
    }
    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Integer id) {
        saleDAO.delete(id);
    }

}



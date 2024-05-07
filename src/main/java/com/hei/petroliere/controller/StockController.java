package com.hei.petroliere.controller;
import com.hei.petroliere.model.Stock;
import com.hei.petroliere.repository.StockDAO;
import com.hei.petroliere.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockDAO stockDAO;

    @Autowired
    public StockController(StockDAO stockDAO, StockService stockService) {
        this.stockDAO = stockDAO;
    }
    @GetMapping
    public List<Stock> getAllStock() {
        return stockDAO.findAll();
    }
    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable Integer id) {
        return stockDAO.findById(id);
    }
    @PostMapping
    public void createStock(@RequestBody Stock newStock) {
        stockDAO.save(newStock);
    }
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Integer id) {
        stockDAO.delete(id);
    }

}



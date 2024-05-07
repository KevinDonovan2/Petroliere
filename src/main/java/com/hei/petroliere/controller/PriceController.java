package com.hei.petroliere.controller;
import com.hei.petroliere.model.Price;
import com.hei.petroliere.repository.PriceDAO;
import com.hei.petroliere.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceDAO priceDAO;

    @Autowired
    public PriceController(PriceDAO priceDAO, PriceService priceService) {
        this.priceDAO = priceDAO;
    }
    @GetMapping
    public List<Price> getAllPrices() {
        return priceDAO.findAll();
    }
    @GetMapping("/{id}")
    public Price getPriceById(@PathVariable Integer id) {
        return priceDAO.findById(id);
    }
    @PostMapping
    public void createPrice(@RequestBody Price newPrice) {
        priceDAO.save(newPrice);
    }
    @DeleteMapping("/{id}")
    public void deletePrice(@PathVariable Integer id) {
        priceDAO.delete(id);
    }

}



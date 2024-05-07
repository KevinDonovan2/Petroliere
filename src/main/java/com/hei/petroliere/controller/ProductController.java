package com.hei.petroliere.controller;
import com.hei.petroliere.model.Product;
import com.hei.petroliere.repository.ProductDAO;
import com.hei.petroliere.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO, ProductService productService) {
        this.productDAO = productDAO;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productDAO.findById(id);
    }
    @PostMapping
    public void createProduct(@RequestBody Product newProduct) {
        productDAO.save(newProduct);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productDAO.delete(id);
    }

}



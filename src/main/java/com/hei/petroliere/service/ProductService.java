package com.hei.petroliere.service;

import com.hei.petroliere.model.Product;
import com.hei.petroliere.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(Integer productId) {
        return productDAO.findById(productId);
    }

    public void createProduct(Product newProduct) {
        productDAO.save(newProduct);
    }

    public void deleteProduct(Integer productId) {
        productDAO.delete(productId);
    }
}

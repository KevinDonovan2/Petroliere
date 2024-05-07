package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private Integer saleId;
    private Instant saleDate;
    private Double quantitySold;
    private Double saleAmount;
    private Integer idStation;
    private Integer idProduct;
}

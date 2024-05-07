package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Integer idStock;
    private Instant stockDate;
    private Double quantityStocked;
    private Integer idStation;
    private Integer idProduct;
}


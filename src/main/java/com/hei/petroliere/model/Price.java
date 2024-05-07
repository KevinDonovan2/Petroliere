package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Integer idPrice;
    private Instant priceDate;
    private Double unitPrice;
    private Integer idProduct;
}



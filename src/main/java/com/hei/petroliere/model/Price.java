package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private int idPrice;
    private int idProduct;
    private Date priceDate;
    private BigDecimal unitPrice;
}



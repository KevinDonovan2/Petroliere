package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private int saleId;
    private int idStation;
    private int idProduct;
    private Date saleDate;
    private BigDecimal quantitySold;
    private BigDecimal saleAmount;
}

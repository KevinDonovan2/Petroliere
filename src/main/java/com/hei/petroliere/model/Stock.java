package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private int idStock;
    private int idStation;
    private int idProduct;
    private Date stockDate;
    private BigDecimal quantityStocked;
}


package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private int stationId;
    private String stationName;
    private String address;
    private BigDecimal evaporationRateGasoline;
    private BigDecimal evaporationRateDiesel;
    private BigDecimal evaporationRateKerosene;
}


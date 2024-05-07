package com.hei.petroliere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private Integer stationId;
    private String stationName;
    private String address;
    private Double evaporationRateGasoline;
    private Double evaporationRateDiesel;
    private Double evaporationRateKerosene;
}


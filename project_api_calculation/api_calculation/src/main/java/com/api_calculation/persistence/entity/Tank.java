package com.api_calculation.persistence.entity;

import lombok.Data;

@Data
public class Tank {

    private Long id;

    private String nombreTk;

    private double capacidadNominal;

    private int inicioZonaCritica;

    private int finalZonaCritica;

    private double tempLamina;

    private double fra1;

    private double fra2;
}

package com.tank_service.service.dto;


import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class InDTOTank {

    @NotNull(message = "Campo vacio, debe enviarlo")
    private String nombreTk;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000.0", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    private double capacidadNominal;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "16000.0", inclusive = false, message = "El valor debe ser menor que 16000.0")
    private double inicioZonaCritica;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "16000.0", inclusive = false, message = "El valor debe ser menor que 16000.0")
    private double finalZonaCritica;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100.0", inclusive = false, message = "El valor debe ser menor que 100.0")
    private double tempLamina;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100.0", inclusive = false, message = "El valor debe ser menor que 100.0")
    private double fra1;
    @NotNull(message = "Campo vacio, debe enviarlo")
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100.0", inclusive = false, message = "El valor debe ser menor que 100.0")
    private double fra2;
}
package com.api_calculation.service.dto;


import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DatosParaEditatLiq {


    @NotNull
    String ABD;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "20000", inclusive = false, message = "El valor debe ser menor que 20000")
    double gauge;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double tov;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "20000", inclusive = false, message = "El valor debe ser menor que 20000.0")
    double waterGauge;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double waterTov;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100", inclusive = false, message = "El valor debe ser menor que 100.0")
    double kFra1;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100", inclusive = false, message = "El valor debe ser menor que 100.0")
    double kFra2;
    @NotNull
    @DecimalMin(value = "-1000", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000", inclusive = false, message = "El valor debe ser menor que 100.0")
    double fra;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "150", inclusive = false, message = "El valor debe ser menor que 100.0")
    double tLam;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "150", inclusive = false, message = "El valor debe ser menor que 100.0")
    double tempL;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100", inclusive = false, message = "El valor debe ser menor que 100.0")
    double tAmb;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "2", inclusive = false, message = "El valor debe ser menor que 2.0")
    double ctsh;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double gov;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "100.0", inclusive = false, message = "El valor debe ser menor que 100.0")
    double api60;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "2", inclusive = false, message = "El valor debe ser menor que 2.0")
    double ctl;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double gsv;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0")
    @DecimalMax(value = "2.0", inclusive = false, message = "El valor debe ser menor que 2.0")
    double bsw;
    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000000", inclusive = false, message = "El valor debe ser menor que 1000000.0")
    double nsv;
    @NotBlank
    String nombreTk;

    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "2", inclusive = false, message = "El valor debe ser menor que 2")
    double water;

    @NotNull
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "2", inclusive = false, message = "El valor debe ser menor que 2")
    double sediment;
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "200", inclusive = false, message = "El valor debe ser menor que 200")
    double salt;


    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "10", inclusive = false, message = "El valor debe ser menor que 10")
    double sulfur;
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "50", inclusive = false, message = "El valor debe ser menor que 50")
    double tan;
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "1000", inclusive = false, message = "El valor debe ser menor que 1000")
    double viscosity;
    @DecimalMin(value = "-1", inclusive = false, message = "El valor debe ser mayor que 0.0")
    @DecimalMax(value = "200", inclusive = false, message = "El valor debe ser menor que 200")
    double flashpoint;



}


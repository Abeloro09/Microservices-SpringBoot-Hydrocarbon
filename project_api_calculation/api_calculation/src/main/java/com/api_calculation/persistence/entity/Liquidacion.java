package com.api_calculation.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Component
@Data
@Entity
@Table(name="liquidaciones")
public class Liquidacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liquidacion")
    private Long id;
    String ABD;
    double gauge;
    double tov;
    double waterGauge;
    double waterTov;
    double kFra1;
    double kFra2;
    double fra;
    double tLam;
    double tempL;
    double tAmb;
    double ctsh;
    double gov;
    double api60;
    double ctl;
    double gsv;
    double bsw;
    double nsv;
    String nombreTk;

    double water;
    double sediment;
    double salt;
    double sulfur;
    double tan;
    double viscosity;
    double flashpoint;


    LocalDate fecha;
    LocalTime hora;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movimiento")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Movimiento movimiento;

}

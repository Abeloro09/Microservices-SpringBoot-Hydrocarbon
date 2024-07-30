package com.tank_service.persistence.entity;

//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tanks")
public class Tank implements Serializable {

    private static final long serialVersionUID = 1234567891234567894L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombretk", nullable = false)
    private String nombreTk;
    @Column(name = "capacidadnominal", nullable = false)
    private double capacidadNominal;
    @Column(name = "iniciozonacritica",nullable = false)
    private double inicioZonaCritica;
    @Column(name = "finalzonacritica",nullable = false)
    private double finalZonaCritica;
    @Column(name = "templamina", nullable = false)
    private double tempLamina;
    @Column(name = "fra1", nullable = false)
    private double fra1;
    @Column(name = "fra2",nullable = false)
    private double fra2;


}

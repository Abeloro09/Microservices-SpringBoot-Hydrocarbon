package com.api_calculation.service.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DatosEditarCargueDTO {
    @NotBlank
    private String referencia;
    @NotBlank
    private String referenciaCliente;
    @NotBlank
    private String comprador;
    @NotBlank
    private String vendedor;
    @NotBlank
    private String destino;
    @NotBlank
    private String inspector;
    @NotBlank
    private String terminal;
    @NotBlank
    private String nombreBuque;
    @NotBlank
    private String instrucciones;
}


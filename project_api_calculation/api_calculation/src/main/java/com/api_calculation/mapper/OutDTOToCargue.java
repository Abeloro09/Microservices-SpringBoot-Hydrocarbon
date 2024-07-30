package com.api_calculation.mapper;

import com.api_calculation.persistence.entity.Cargue;
import com.api_calculation.service.dto.OutDTOCargue;

/**
 * Implementation of the IMapper interface for mapping {@link Cargue} to {@link OutDTOCargue}.
 */
public class OutDTOToCargue implements IMapper<Cargue, OutDTOCargue> {

    /**
     * Maps a {@link Cargue} entity to an {@link OutDTOCargue} Data Transfer Object (DTO).
     *
     * @param in The Cargue entity to be mapped.
     * @return The mapped OutDTOCargue DTO.
     */
    @Override
    public OutDTOCargue map(Cargue in) {
        // Instantiate a new OutDTOCargue object
        OutDTOCargue outDTOCargue = new OutDTOCargue();

        // Set properties of the OutDTOCargue DTO from the Cargue entity
        outDTOCargue.setId(in.getId());
        outDTOCargue.setReferencia(in.getReferencia());
        outDTOCargue.setReferenciaCliente(in.getReferenciaCliente());
        outDTOCargue.setComprador(in.getComprador());
        outDTOCargue.setVendedor(in.getVendedor());
        outDTOCargue.setNombreBuque(in.getNombreBuque());

        return outDTOCargue;
    }
}
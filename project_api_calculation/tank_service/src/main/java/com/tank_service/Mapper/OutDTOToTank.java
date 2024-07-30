package com.tank_service.Mapper;

import com.tank_service.persistence.entity.Tank;
import com.tank_service.service.dto.OutDTOTank;
import org.springframework.stereotype.Component;

@Component
public class OutDTOToTank implements IMapper<Tank, OutDTOTank>{
    @Override
    public OutDTOTank map(Tank in) {
        OutDTOTank outDTOTank = new OutDTOTank();
        outDTOTank.setId(in.getId());
        outDTOTank.setNombreTk(in.getNombreTk());
        outDTOTank.setCapacidadNominal(in.getCapacidadNominal());
        return outDTOTank;
    }
}

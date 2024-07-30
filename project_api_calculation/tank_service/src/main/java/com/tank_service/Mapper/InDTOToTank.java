package com.tank_service.Mapper;

import com.tank_service.persistence.entity.Tank;
import com.tank_service.service.dto.InDTOTank;
import org.springframework.stereotype.Component;

@Component
public class InDTOToTank implements IMapper<InDTOTank, Tank>{

    @Override
    public Tank map(InDTOTank in) {
        Tank tank = new Tank();

        tank.setNombreTk(in.getNombreTk());
        tank.setCapacidadNominal(in.getCapacidadNominal());
        tank.setInicioZonaCritica(in.getInicioZonaCritica());
        tank.setFinalZonaCritica(in.getFinalZonaCritica());
        tank.setTempLamina(in.getTempLamina());
        tank.setFra1(in.getFra1());
        tank.setFra1(in.getFra2());

        return tank;
    }
}

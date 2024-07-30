package com.api_calculation.mapper;

import com.api_calculation.client.TankClientRest;
import com.api_calculation.persistence.entity.Liquidacion;
import com.api_calculation.persistence.entity.Tank;
import com.api_calculation.service.dto.LiquidacionInDTO;
import com.api_calculation.util.CalculationsLiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
/**
 * Implementation of the IMapper interface for mapping {@link LiquidacionInDTO} to {@link Liquidacion}.
 */
public class LiquidacionInDTOToLiquidacion implements IMapper<LiquidacionInDTO, Liquidacion> {

    private final TankClientRest tankClientRest;

    /**
     * Constructor to initialize the mapper with a TankClientRest instance.
     *
     * @param tankClientRest Client for interacting with tank-related services.
     */
    public LiquidacionInDTOToLiquidacion(TankClientRest tankClientRest) {
        this.tankClientRest = tankClientRest;
    }

    /**
     * Maps a {@link LiquidacionInDTO} to a {@link Liquidacion} entity.
     *
     * @param in The LiquidacionInDTO object to be mapped.
     * @return The mapped Liquidacion entity.
     */
    @Override
    public Liquidacion map(LiquidacionInDTO in) {

        // Retrieve the Tank object associated with the given ID from the DTO
        Optional<Tank> tank = tankClientRest.findById(in.getIdTank());

        // Instantiate a new Liquidacion entity
        Liquidacion liquidacion = new Liquidacion();

        // Instantiate CalculationsLiq with values from the DTO and Tank object
        CalculationsLiq calculationsLiq = new CalculationsLiq(
                in.getABD(),
                in.getGauge(),
                in.getTov(),
                in.getWaterGauge(),
                in.getWaterTov(),
                tank.get().getFra1(),
                tank.get().getFra2(),
                tank.get().getTempLamina(),
                in.getTempL(),
                in.getTAmb(),
                in.getApi(),
                in.getWater() + in.getSediment()
        );

        // Set properties of the Liquidacion entity from the DTO and Tank object
        liquidacion.setABD(in.getABD());
        liquidacion.setGauge(in.getGauge());
        liquidacion.setTov(in.getTov());
        liquidacion.setWaterGauge(in.getWaterGauge());
        liquidacion.setWaterTov(in.getWaterTov());
        liquidacion.setKFra1(tank.get().getFra1());
        liquidacion.setKFra2(tank.get().getFra2());
        liquidacion.setTLam(tank.get().getTempLamina());
        liquidacion.setTempL(in.getTempL());
        liquidacion.setTAmb(in.getTAmb());
        liquidacion.setApi60(in.getApi());
        liquidacion.setBsw(in.getWater() + in.getSediment());
        liquidacion.setNombreTk(tank.get().getNombreTk());

        // Set calculated values for Liquidacion based on the CalculationsLiq instance
        liquidacion.setFra(calculationsLiq.fra());
        liquidacion.setCtsh(calculationsLiq.ctsh());
        liquidacion.setGov(calculationsLiq.gov());
        liquidacion.setCtl(calculationsLiq.ctl());
        liquidacion.setGsv(calculationsLiq.gsv());
        liquidacion.setNsv(calculationsLiq.nsv());

        return liquidacion;
    }
}
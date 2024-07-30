package com.api_calculation.mapper;

import com.api_calculation.persistence.entity.Liquidacion;
import com.api_calculation.service.MovimientoService;
import com.api_calculation.service.dto.DatosParaEditatLiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// recibe un objeto de liquidacion sin el atributo movimiento
@Component
/**
 * Implementation of the IMapper interface for mapping {@link DatosParaEditatLiq} to {@link Liquidacion}.
 */
public class LiqViejaInDTOToLiqNueva implements IMapper<DatosParaEditatLiq, Liquidacion> {

    /**
     * Maps a {@link DatosParaEditatLiq} to a {@link Liquidacion} entity.
     *
     * @param in The DatosParaEditatLiq object to be mapped.
     * @return The mapped Liquidacion entity.
     */
    @Override
    public Liquidacion map(DatosParaEditatLiq in) {

        // Instantiate a new Liquidacion entity
        Liquidacion nuevaLiquidacion = new Liquidacion();

        // Set properties of the Liquidacion entity from the DTO
        nuevaLiquidacion.setABD(in.getABD());
        nuevaLiquidacion.setGauge(in.getGauge());
        nuevaLiquidacion.setTov(in.getTov());
        nuevaLiquidacion.setWaterGauge(in.getWaterGauge());
        nuevaLiquidacion.setWaterTov(in.getWaterTov());
        nuevaLiquidacion.setKFra1(in.getKFra1());
        nuevaLiquidacion.setKFra2(in.getKFra2());
        nuevaLiquidacion.setTLam(in.getTLam());
        nuevaLiquidacion.setTempL(in.getTempL());
        nuevaLiquidacion.setTAmb(in.getTAmb());
        nuevaLiquidacion.setApi60(in.getApi60());
        nuevaLiquidacion.setBsw(in.getBsw());
        nuevaLiquidacion.setNombreTk(in.getNombreTk());

        // Set additional properties of the Liquidacion entity from the DTO
        nuevaLiquidacion.setFra(in.getFra());
        nuevaLiquidacion.setCtsh(in.getCtsh());
        nuevaLiquidacion.setGov(in.getGov());
        nuevaLiquidacion.setCtl(in.getCtl());
        nuevaLiquidacion.setGsv(in.getGsv());
        nuevaLiquidacion.setNsv(in.getNsv());

        return nuevaLiquidacion;
    }
}
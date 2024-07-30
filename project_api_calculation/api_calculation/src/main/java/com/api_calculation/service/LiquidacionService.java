package com.api_calculation.service;

import com.api_calculation.mapper.LiquidacionInDTOToLiquidacion;
import com.api_calculation.persistence.entity.Liquidacion;
import com.api_calculation.persistence.entity.Movimiento;
import com.api_calculation.persistence.repository.LiquidacionRepository;
import com.api_calculation.service.dto.LiquidacionInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Service for managing operations related to the {@link Liquidacion} entity.
 * Provides methods to create, find, update, and delete Liquidacion entities.
 */
public class LiquidacionService {

    // Autowired mapper for converting LiquidacionInDTO to Liquidacion
    @Autowired
    private LiquidacionInDTOToLiquidacion mapper;

    // Autowired Liquidacion object
    @Autowired
    private Liquidacion liquidacion;

    // Autowired repository for Liquidacion
    @Autowired
    private LiquidacionRepository liquidacionRepository;

    /**
     * Creates a new Liquidacion entity based on the provided DTO and ID.
     *
     * @param liquidacionInDTO Data transfer object containing the information to create a new Liquidacion.
     * @param id ID to be associated with the new Liquidacion.
     * @return The newly created Liquidacion entity.
     */
    public Liquidacion createLiquidacion(LiquidacionInDTO liquidacionInDTO, Long id){
        // Map DTO to Liquidacion entity
        Liquidacion liquidacion = mapper.map(liquidacionInDTO);
        // Save Liquidacion entity to repository
        liquidacionRepository.save(liquidacion);
        return liquidacion;
    }

    /**
     * Creates an initial Liquidacion entity based on the provided Movimiento.
     *
     * @param movimiento Movimiento entity used to initialize the Liquidacion.
     * @return The newly created initial Liquidacion entity.
     */
    public Liquidacion crearLiqInicial(Movimiento movimiento){
        // Create a new Liquidacion entity with initial values
        Liquidacion liquidacionInicial = new Liquidacion();
        liquidacionInicial.setABD("A");
        liquidacionInicial.setGauge(0);
        liquidacionInicial.setTov(0);
        liquidacionInicial.setWaterGauge(0);
        liquidacionInicial.setWaterTov(0);
        liquidacionInicial.setKFra1(0);
        liquidacionInicial.setKFra2(0);
        liquidacionInicial.setTLam(0);
        liquidacionInicial.setTempL(0);
        liquidacionInicial.setTAmb(0);
        liquidacionInicial.setApi60(0);
        liquidacionInicial.setBsw(0);

        liquidacionInicial.setFra(0);
        liquidacionInicial.setCtsh(0);
        liquidacionInicial.setGov(0);
        liquidacionInicial.setCtl(0);
        liquidacionInicial.setGsv(0);
        liquidacionInicial.setNsv(0);
        liquidacionInicial.setMovimiento(movimiento);

        // Save initial Liquidacion entity to repository
        return liquidacionRepository.save(liquidacionInicial);
    }

    /**
     * Processes the provided LiquidacionInDTO to create or update a Liquidacion entity.
     *
     * @param liquidacionInDTO Data transfer object containing the information to process.
     * @return The Liquidacion entity created or updated from the DTO.
     */
    public Liquidacion liquidar(LiquidacionInDTO liquidacionInDTO){
        // Map DTO to Liquidacion entity
        Liquidacion liquidacion = mapper.map(liquidacionInDTO);
        return liquidacion;
    }

    /**
     * Finds all Liquidacion entities in the repository.
     *
     * @return A list of all Liquidacion entities.
     */
    public List<Liquidacion> findAll(){
        return this.liquidacionRepository.findAll();
    }

    /**
     * Finds a Liquidacion entity by its ID.
     *
     * @param id ID of the Liquidacion entity to find.
     * @return An {@link Optional} containing the Liquidacion entity if found, or empty if not.
     */
    public Optional<Liquidacion> findById(Long id){
        return liquidacionRepository.findById(id);
    }

    /**
     * Updates an existing Liquidacion entity and saves it to the repository.
     *
     * @param liquidacion The Liquidacion entity with updated data.
     * @return The updated Liquidacion entity.
     */
    public Liquidacion updateLiquidacion(Liquidacion liquidacion){
        return this.liquidacionRepository.save(liquidacion);
    }

    /**
     * Deletes a Liquidacion entity by its ID and returns a list of all remaining entities.
     *
     * @param id ID of the Liquidacion entity to delete.
     * @return A list of all remaining Liquidacion entities after deletion.
     */
    public List<Liquidacion> delete(Long id){
        liquidacionRepository.deleteById(id);
        return this.liquidacionRepository.findAll();
    }
}
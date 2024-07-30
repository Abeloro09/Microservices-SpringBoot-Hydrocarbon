package com.api_calculation.service;

import com.api_calculation.client.TankClientRest;
import com.api_calculation.mapper.LiqViejaInDTOToLiqNueva;
import com.api_calculation.mapper.LiquidacionInDTOToLiquidacion;
import com.api_calculation.persistence.entity.Liquidacion;
import com.api_calculation.persistence.entity.Movimiento;
import com.api_calculation.persistence.repository.MovimientoRepository;
import com.api_calculation.service.dto.DatosParaEditatLiq;
import com.api_calculation.service.dto.LiquidacionInDTO;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
/**
 * Service for managing operations related to the {@link Movimiento} entity.
 * Provides methods to create, find, update, and delete Movimiento entities,
 * and to perform calculations related to Liquidacion.
 */
public class MovimientoService {

    private MovimientoRepository movimientoRepository;
    private final LiquidacionService liquidacionService;
    private LiquidacionInDTOToLiquidacion mapper;
    private LiqViejaInDTOToLiqNueva mapper2;

    private TankClientRest tankClientRest;
    private CargueService cargueService;

    /**
     * Constructor to initialize all the dependencies for the MovimientoService.
     *
     * @param movimientoRepository Repository for Movimiento entities.
     * @param liquidacionService Service for managing Liquidacion entities.
     * @param mapper Mapper to convert LiquidacionInDTO to Liquidacion.
     * @param mapper2 Mapper to convert LiqViejaInDTO to LiqNueva.
     * @param tankClientRest Client for interacting with tank-related services.
     * @param cargueService Service for managing Cargue entities.
     */
    public MovimientoService(MovimientoRepository movimientoRepository,
                             LiquidacionService liquidacionService,
                             LiquidacionInDTOToLiquidacion mapper,
                             LiqViejaInDTOToLiqNueva mapper2,
                             TankClientRest tankClientRest,
                             CargueService cargueService) {

        this.movimientoRepository = movimientoRepository;
        this.liquidacionService = liquidacionService;
        this.mapper = mapper;
        this.mapper2 = mapper2;
        this.tankClientRest = tankClientRest;
        this.cargueService = cargueService;
    }

    /**
     * Creates a new Movimiento and associated Liquidacion entities.
     *
     * @param cargueId ID of the Cargue to associate with the Movimiento.
     * @return The newly created Movimiento entity with associated Liquidacion entities.
     */
    public Movimiento createMovimiento(Long cargueId){

        // Create an empty list for Liquidacion
        List<Liquidacion> listaLiquidaciones = new ArrayList<>();

        // Create an empty Movimiento
        Movimiento movimiento = new Movimiento();
        // Assign the Movimiento to a Cargue
        movimiento.setCargue(cargueService.findById(cargueId).get());
        // Save the Movimiento to the database
        movimientoRepository.save(movimiento);

        // Create and save initial and final Liquidacion entities to the database
        Liquidacion liquidacionInicial = liquidacionService.crearLiqInicial(movimiento);
        Liquidacion liquidacionFinal = liquidacionService.crearLiqInicial(movimiento);

        // Add Liquidacion entities to the list
        listaLiquidaciones.add(liquidacionInicial);
        listaLiquidaciones.add(liquidacionFinal);

        // Set the Liquidacion list and differences in the Movimiento
        movimiento.setListaLIquidaciones(listaLiquidaciones);
        movimiento.setDifTOV(0);
        movimiento.setDifFw(0);
        movimiento.setDifGsv(0);
        movimiento.setDifNsv(0);
        movimiento.setCargue(cargueService.findById(cargueId).get());

        // Save the Movimiento to the database again
        movimientoRepository.save(movimiento);

        return movimiento;
    }

    /**
     * Finds all Movimiento entities in the repository.
     *
     * @return A list of all Movimiento entities.
     */
    public List<Movimiento> findAll(){
        return movimientoRepository.findAll();
    }

    /**
     * Finds all Movimiento entities with pagination.
     *
     * @param paginacion Pagination information.
     * @return A page of Movimiento entities.
     */
    public Page<Movimiento> findAll(Pageable paginacion){
        return movimientoRepository.findAll(paginacion);
    }

    /**
     * Finds a Movimiento entity by its ID.
     *
     * @param id ID of the Movimiento entity to find.
     * @return An {@link Optional} containing the Movimiento entity if found, or empty if not.
     */
    public Optional<Movimiento> findById(Long id){
        return movimientoRepository.findById(id);
    }

    /**
     * Updates an existing Movimiento entity and saves it to the repository.
     *
     * @param movimiento The Movimiento entity with updated data.
     * @return The updated Movimiento entity.
     */
    public Movimiento updateMovimiento(Movimiento movimiento){
        return this.movimientoRepository.save(movimiento);
    }

    /**
     * Calculates a Liquidacion based on the provided input data and updates the associated Movimiento.
     *
     * @param in Data transfer object containing the information for the Liquidacion.
     * @param id ID of the existing Liquidacion to update.
     * @return The updated Movimiento entity after calculation.
     */
    public Movimiento calcularLiquidacion(LiquidacionInDTO in, Long id){

        Optional<Liquidacion> liquidacionAcambiar = liquidacionService.findById(id);
        Movimiento movimiento = liquidacionAcambiar.get().getMovimiento();
        Liquidacion liquidacionNueva = liquidacionAcambiar.get();

        // Map input data to a Liquidacion entity
        Liquidacion liquidacioATomar = mapper.map(in);

        // Update the initial Liquidacion values
        Liquidacion liquidacionActualizada = actualizarValoresLiq(liquidacioATomar, liquidacionNueva, movimiento);

        // Save the updated Liquidacion to the database
        liquidacionService.updateLiquidacion(liquidacionActualizada);

        // Calculate the differences between Movimientos
        Movimiento movimientoCalculado = calcularDiferencias(movimiento);

        // Save the updated Movimiento to the database
        movimientoRepository.save(movimientoCalculado);

        return movimiento;
    }

    /**
     * Edits a Liquidacion based on the provided input data and updates the associated Movimiento.
     *
     * @param datosParaEditatLiq Data transfer object containing the information to edit the Liquidacion.
     * @param id ID of the existing Liquidacion to update.
     * @return The updated Movimiento entity after editing.
     */
    public Movimiento editarLiquidacion(DatosParaEditatLiq datosParaEditatLiq, Long id){

        Optional<Liquidacion> liquidacionAcambiar = liquidacionService.findById(id);
        Movimiento movimiento = liquidacionAcambiar.get().getMovimiento();
        Liquidacion liquidacionNueva = liquidacionAcambiar.get();

        // Map input data to a Liquidacion entity without Movimiento attribute
        Liquidacion liquidacioATomar = mapper2.map(datosParaEditatLiq);

        // Update the initial Liquidacion values
        Liquidacion liquidacionActualizada = actualizarValoresLiq(liquidacioATomar, liquidacionNueva, movimiento);

        // Save the updated Liquidacion to the database
        liquidacionService.updateLiquidacion(liquidacionActualizada);

        // Calculate the differences between Movimientos
        Movimiento movimientoCalculado = calcularDiferencias(movimiento);

        // Save the updated Movimiento to the database
        movimientoRepository.save(movimientoCalculado);

        return movimiento;
    }

    /**
     * Calculates differences between initial and final Liquidacion values in a Movimiento.
     *
     * @param movimiento The Movimiento entity containing the Liquidacion entities.
     * @return The Movimiento entity with updated difference values.
     */
    public Movimiento calcularDiferencias(Movimiento movimiento){

        double tovIni = movimiento.getListaLIquidaciones().get(0).getTov();
        double tovFin = movimiento.getListaLIquidaciones().get(1).getTov();
        movimiento.setDifTOV(tovIni - tovFin);

        double fwIni = movimiento.getListaLIquidaciones().get(0).getWaterTov();
        double fwvFin = movimiento.getListaLIquidaciones().get(1).getWaterTov();
        movimiento.setDifFw(fwIni - fwvFin);

        double gsvIni = movimiento.getListaLIquidaciones().get(0).getGsv();
        double gsvFin = movimiento.getListaLIquidaciones().get(1).getGsv();
        movimiento.setDifGsv(gsvIni - gsvFin);

        double nsvIni = movimiento.getListaLIquidaciones().get(0).getNsv();
        double nsvFin = movimiento.getListaLIquidaciones().get(1).getNsv();
        movimiento.setDifNsv(nsvIni - nsvFin);

        return movimiento;
    }

    /**
     * Updates Liquidacion values with the provided data.
     *
     * @param liquidacioATomar New Liquidacion data to apply.
     * @param liquidacionNueva Existing Liquidacion to update.
     * @param movimiento Movimiento entity associated with the Liquidacion.
     * @return The updated Liquidacion entity.
     */
    public Liquidacion actualizarValoresLiq(Liquidacion liquidacioATomar, Liquidacion liquidacionNueva, Movimiento movimiento){

        liquidacionNueva.setABD(liquidacioATomar.getABD());
        liquidacionNueva.setGauge(liquidacioATomar.getGauge());
        liquidacionNueva.setTov(liquidacioATomar.getTov());
        liquidacionNueva.setWaterGauge(liquidacioATomar.getWaterGauge());
        liquidacionNueva.setWaterTov(liquidacioATomar.getWaterTov());
        liquidacionNueva.setKFra1(liquidacioATomar.getKFra1());
        liquidacionNueva.setKFra2(liquidacioATomar.getKFra2());
        liquidacionNueva.setTLam(liquidacioATomar.getTLam());
        liquidacionNueva.setTempL(liquidacioATomar.getTempL());
        liquidacionNueva.setTAmb(liquidacioATomar.getTAmb());
        liquidacionNueva.setApi60(liquidacioATomar.getApi60());
        liquidacionNueva.setBsw(liquidacioATomar.getBsw());
        liquidacionNueva.setNombreTk(liquidacioATomar.getNombreTk());
        liquidacionNueva.setMovimiento(movimiento);

        liquidacionNueva.setFra(liquidacioATomar.getFra());
        liquidacionNueva.setCtsh(liquidacioATomar.getCtsh());
        liquidacionNueva.setGov(liquidacioATomar.getGov());
        liquidacionNueva.setCtl(liquidacioATomar.getCtl());
        liquidacionNueva.setGsv(liquidacioATomar.getGsv());
        liquidacionNueva.setNsv(liquidacioATomar.getNsv());

        return liquidacionNueva;
    }

    /**
     * Deletes a Movimiento entity by its ID.
     *
     * @param id ID of the Movimiento entity to delete.
     */
    public void eliminarMovimiento(Long id){
        movimientoRepository.deleteById(id);
    }
}
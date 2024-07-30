package com.api_calculation.controller;

import com.api_calculation.persistence.entity.Movimiento;
import com.api_calculation.persistence.repository.MovimientoRepository;
import com.api_calculation.service.MovimientoService;
import com.api_calculation.service.dto.DatosParaEditatLiq;
import com.api_calculation.service.dto.LiquidacionInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Controller for managing Movimiento entities.
 */
@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;
    private final MovimientoRepository movimientoRepository;

    @Autowired
    private Environment env;

    /**
     * Constructs a MovimientoController with the specified MovimientoService and MovimientoRepository.
     *
     * @param movimientoService The service for managing Movimiento entities.
     * @param movimientoRepository The repository for Movimiento entities.
     */
    public MovimientoController(MovimientoService movimientoService,
                                MovimientoRepository movimientoRepository) {
        this.movimientoService = movimientoService;
        this.movimientoRepository = movimientoRepository;
    }

    /**
     * Creates a new Movimiento and returns the response with the created Movimiento.
     *
     * @param cargueId The ID of the Cargue associated with the Movimiento.
     * @param uriComponentsBuilder The UriComponentsBuilder for building the URI of the created Movimiento.
     * @return A ResponseEntity containing the created Movimiento.
     */
    @PostMapping("/crearmovimiento/{cargueId}")
    public ResponseEntity<Movimiento> createMovimiento(@PathVariable("cargueId") Long cargueId, UriComponentsBuilder uriComponentsBuilder) {
        Movimiento movimiento = this.movimientoService.createMovimiento(cargueId);
        URI url = uriComponentsBuilder.path("/movimiento/{id}").buildAndExpand(movimiento.getId()).toUri();
        return ResponseEntity.created(url).body(movimiento);
    }

    /**
     * Retrieves all Movimiento entities with pagination.
     *
     * @param paginacion The Pageable object for pagination.
     * @return A ResponseEntity containing a Page of Movimiento entities.
     */
    @GetMapping("/movimientos")
    public ResponseEntity<Page<Movimiento>> findAll(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(this.movimientoService.findAll(paginacion));
    }

    /**
     * Retrieves a Movimiento entity by its ID.
     *
     * @param id The ID of the Movimiento.
     * @return A ResponseEntity containing the Movimiento entity if found, otherwise a 404 Not Found status.
     */
    @GetMapping("/movimientoById/{id}")
    public ResponseEntity<Optional<Movimiento>> findById(@PathVariable("id") Long id) {
        Optional<Movimiento> movimientoOptional = this.movimientoService.findById(id);

        if (movimientoOptional.isPresent()) {
            return ResponseEntity.ok(movimientoOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Calculates the Liquidacion for a Movimiento based on the provided LiquidacionInDTO.
     *
     * @param id The ID of the Movimiento.
     * @param liquidacionInDTO The LiquidacionInDTO containing the data for the calculation.
     * @return A ResponseEntity containing the updated Movimiento with the calculated Liquidacion.
     */
    @PutMapping("/calcularLiquidacion/{id}/")
    public ResponseEntity<Optional<Movimiento>> calcularLiquidacion(@PathVariable("id") Long id,
                                                                    @Valid @RequestBody LiquidacionInDTO liquidacionInDTO) {
        return ResponseEntity.ok(Optional.ofNullable(movimientoService.calcularLiquidacion(liquidacionInDTO, id)));
    }

    /**
     * Edits the Liquidacion for a Movimiento based on the provided DatosParaEditatLiq.
     *
     * @param id The ID of the Movimiento.
     * @param datosParaEditatLiq The DatosParaEditatLiq containing the data for editing the Liquidacion.
     * @return A ResponseEntity containing the updated Movimiento with the edited Liquidacion.
     */
    @PutMapping("/editarLiquidacion/{id}/")
    public ResponseEntity<Optional<Movimiento>> editarLiquidacion(@PathVariable("id") Long id,
                                                                  @Valid @RequestBody DatosParaEditatLiq datosParaEditatLiq) {
        return ResponseEntity.ok(Optional.ofNullable(movimientoService.editarLiquidacion(datosParaEditatLiq, id)));
    }

    /**
     * Deletes a Movimiento by its ID.
     *
     * @param id The ID of the Movimiento to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/eliminarMovimiento/{id}/")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable("id") Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    // Testing retrieving configuration from the configuration server
}
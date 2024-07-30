package com.api_calculation.controller;

import com.api_calculation.persistence.entity.Cargue;
import com.api_calculation.service.CargueService;
import com.api_calculation.service.dto.DatosEditarCargueDTO;
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
 * Controller for managing Cargue entities.
 */
@RestController
@RequestMapping("/cargue")
public class CargueController {

    private final CargueService cargueService;

    /**
     * Constructor to initialize CargueService.
     *
     * @param cargueService the service used to manage Cargue entities
     */
    public CargueController(CargueService cargueService) {
        this.cargueService = cargueService;
    }

    /**
     * Endpoint to create a new Cargue entity.
     *
     * @param uriComponentsBuilder used to build the URI for the created Cargue
     * @return a ResponseEntity containing the created Cargue and the location of the new resource
     */
    @PostMapping
    public ResponseEntity<Cargue> crearCargue(UriComponentsBuilder uriComponentsBuilder) {
        Cargue cargue = this.cargueService.createCargue();
        URI url = uriComponentsBuilder.path("/cargue/{id}").buildAndExpand(cargue.getId()).toUri();
        return ResponseEntity.created(url).body(cargue);
    }

    /**
     * Endpoint to find a Cargue entity by its ID.
     *
     * @param cargueId the ID of the Cargue to find
     * @return a ResponseEntity containing the Cargue if found, or a NOT_FOUND status if not
     */
    @GetMapping("/cargueById/{cargueId}")
    public ResponseEntity<Optional<Cargue>> findById(@PathVariable("cargueId") Long cargueId) {
        Optional<Cargue> cargueOptional = this.cargueService.findById(cargueId);

        if (cargueOptional.isPresent()) {
            return ResponseEntity.ok(cargueOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to retrieve all Cargue entities with pagination.
     *
     * @param paginacion the pagination information
     * @return a ResponseEntity containing a page of Cargue entities
     */
    @GetMapping("/cargues")
    public ResponseEntity<Page<Cargue>> findAll(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(this.cargueService.findAll(paginacion));
    }

    /**
     * Endpoint to delete a Cargue entity by its ID.
     *
     * @param id the ID of the Cargue to delete
     * @return a ResponseEntity with a NO_CONTENT status
     */
    @DeleteMapping("/eliminarCargue/{id}/")
    public ResponseEntity<Page<Cargue>> eliminarCargue(@PathVariable("id") Long id, @PageableDefault(size = 2) Pageable paginacion) {
        cargueService.eliminarCargue(id);
        return ResponseEntity.ok(this.cargueService.findAll(paginacion));
        //return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint to update an existing Cargue entity.
     *
     * @param id the ID of the Cargue to update
     * @param datosEditarCargueDTO the data to update the Cargue with
     * @return a ResponseEntity containing the updated Cargue
     */
    @PutMapping("/editarCargue/{id}/")
    public ResponseEntity<Cargue> editarCargue(@PathVariable("id") Long id, @Valid @RequestBody DatosEditarCargueDTO datosEditarCargueDTO) {
        return ResponseEntity.ok(this.cargueService.updateCargue(id, datosEditarCargueDTO));
    }
}

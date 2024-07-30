package com.api_calculation.service;

import com.api_calculation.persistence.entity.Cargue;
import com.api_calculation.persistence.repository.CargueRepository;
import com.api_calculation.service.dto.DatosEditarCargueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing operations related to the {@link Cargue} entity.
 * Provides methods to create, find, update, and delete Cargue entities.
 */
@Service
public class CargueService {

    private CargueRepository cargueRepository;

    /**
     * Constructor to initialize the Cargue repository.
     *
     * @param cargueRepository Repository for the Cargue entity.
     */
    public CargueService(CargueRepository cargueRepository) {
        this.cargueRepository = cargueRepository;
    }

    /**
     * Creates a new Cargue entity and saves it to the database.
     *
     * @return The newly created Cargue entity.
     */
    public Cargue createCargue() {

        // Create a new instance of Cargue
        Cargue cargue = new Cargue();
        cargueRepository.save(cargue);

        return cargue;
    }

    /**
     * Finds a Cargue entity by its ID.
     *
     * @param cargueId ID of the Cargue entity to find.
     * @return An {@link Optional} containing the Cargue entity if found, or empty if not.
     */
    public Optional<Cargue> findById(Long cargueId) {
        return cargueRepository.findById(cargueId);
    }

    /**
     * Finds all Cargue entities.
     *
     * @return A list of all Cargue entities.
     */
    public List<Cargue> findAll(){
        return cargueRepository.findAll();
    }

    /**
     * Finds all Cargue entities with pagination.
     *
     * @param paginacion Pagination information.
     * @return A page of Cargue entities.
     */
    public Page<Cargue> findAll(Pageable paginacion){
        return cargueRepository.findAll(paginacion);
    }

    /**
     * Deletes a Cargue entity by its ID.
     *
     * @param id ID of the Cargue entity to delete.
     */
    public void eliminarCargue(Long id) {
        cargueRepository.deleteById(id);
    }

    /**
     * Updates a Cargue entity with new data and saves it to the database.
     *
     * @param id ID of the Cargue entity to update.
     * @param datosEditarCargueDTO New data to update the Cargue entity.
     * @return The updated Cargue entity.
     */
    public Cargue updateCargue(Long id, DatosEditarCargueDTO datosEditarCargueDTO) {

        // Retrieve the existing Cargue entity by its ID
        Cargue updatedCargue = this.cargueRepository.findById(id).get();

        // Update the Cargue entity with the new data
        updatedCargue.setComprador(datosEditarCargueDTO.getComprador());
        updatedCargue.setVendedor(datosEditarCargueDTO.getVendedor());
        updatedCargue.setDestino(datosEditarCargueDTO.getDestino());
        updatedCargue.setInspector(datosEditarCargueDTO.getInspector());
        updatedCargue.setReferencia(datosEditarCargueDTO.getReferencia());
        updatedCargue.setReferenciaCliente(datosEditarCargueDTO.getReferenciaCliente());
        updatedCargue.setTerminal(datosEditarCargueDTO.getTerminal());
        updatedCargue.setNombreBuque(datosEditarCargueDTO.getNombreBuque());

        // Save the updated Cargue entity to the database
        return cargueRepository.save(updatedCargue);
    }
}
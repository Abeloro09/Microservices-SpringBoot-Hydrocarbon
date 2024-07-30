package com.api_calculation.client;

import com.api_calculation.persistence.entity.Tank;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;



import java.util.List;
import java.util.Optional;

/**
 * Feign client interface for interacting with the "ms-infotank" microservice.
 * It provides methods to access tank information.
 */
@FeignClient(name = "ms-infotank", url = "localhost:8001")
public interface TankClientRest {

    /**
     * Retrieves a list of all tanks.
     *
     * @return a list of {@link Tank} objects.
     */
    @GetMapping
    public List<Tank> findAll();

    /**
     * Retrieves a tank by its unique identifier.
     *
     * @param id the unique identifier of the tank.
     * @return an {@link Optional} containing the {@link Tank} if found, or an empty {@link Optional} if not found.
     */
    @GetMapping("/tank/byId/{id}")
    public Optional<Tank> findById(@PathVariable("id") Long id);
}
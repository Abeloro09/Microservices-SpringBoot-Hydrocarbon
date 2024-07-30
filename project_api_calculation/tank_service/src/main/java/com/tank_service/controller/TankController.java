package com.tank_service.controller;

import com.tank_service.Mapper.OutDTOToTank;
import com.tank_service.persistence.entity.Tank;
import com.tank_service.service.TankService;
import com.tank_service.service.dto.InDTOTank;
import com.tank_service.service.dto.OutDTOTank;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping("/tank")
public class TankController {

    @Autowired
    private Environment env;


    private final TankService tankService;
    private final OutDTOToTank outDTOToTank;

    public TankController(TankService tankService, OutDTOToTank outDTOToTank) {

        this.tankService = tankService;
        this.outDTOToTank = outDTOToTank;
    }
    @PostMapping
    public ResponseEntity<Tank> createTank(@RequestBody @Valid InDTOTank inDTOTank, UriComponentsBuilder uriComponentsBuilder){
        Tank tank = this.tankService.createTank(inDTOTank);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(tank.getId()).toUri();
        return ResponseEntity.created(url).body(tank);

    }

    @GetMapping
    public ResponseEntity<Page<OutDTOTank>> findAll(@PageableDefault(size = 2) Pageable paginacion){

        return ResponseEntity.ok(this.tankService.findAll(paginacion).map(outDTOToTank::map));

    }


    @GetMapping("/byId/{id}")
    public ResponseEntity<Optional<Tank>> findById(@PathVariable("id") Long id){

        Optional <Tank> tankOptional = this.tankService.findById(id);
        if (tankOptional.isPresent()){
            return ResponseEntity.ok(tankOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tank> update(@PathVariable("id") Long id, @RequestBody @Valid InDTOTank inDTOTank){
        Tank updatedTank = tankService.findById(id).get();

        updatedTank.setNombreTk(inDTOTank.getNombreTk());
        updatedTank.setCapacidadNominal(inDTOTank.getCapacidadNominal());
        updatedTank.setInicioZonaCritica(inDTOTank.getInicioZonaCritica());
        updatedTank.setFinalZonaCritica(inDTOTank.getFinalZonaCritica());
        updatedTank.setTempLamina(inDTOTank.getTempLamina());
        updatedTank.setFra1(inDTOTank.getFra1());
        updatedTank.setFra2(inDTOTank.getFra2());

        tankService.updateTank(updatedTank);

        return ResponseEntity.ok(updatedTank);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTank(@PathVariable("id") Long id){
        tankService.deleteTank(id);
        return ResponseEntity.ok().build();

    }
    // implementando metodos para obtener configuraciones



}

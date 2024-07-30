package com.tank_service.service;

import com.tank_service.Mapper.InDTOToTank;
import com.tank_service.persistence.entity.Tank;
import com.tank_service.persistence.repository.TankRepository;
import com.tank_service.service.dto.InDTOTank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TankService {

    private final TankRepository tankReposotory;
    private InDTOToTank inDTOToTank;

    public TankService(TankRepository repository, InDTOToTank inDTOToTank){

        this.tankReposotory = repository;
        this.inDTOToTank= inDTOToTank;
    }

    public Tank createTank(InDTOTank inDTOTank){
        Tank tank = inDTOToTank.map(inDTOTank);
        return this.tankReposotory.save(tank);
    }

    public List<Tank> findAll() {
        return this.tankReposotory.findAll();
    }

    public Page<Tank> findAll(Pageable paginacion) {

        return this.tankReposotory.findAll(paginacion);
    }


    public Optional<Tank> findById(Long id){
        return this.tankReposotory.findById(id);
    }

    public Tank updateTank(Tank tank){ return this.tankReposotory.save(tank);
    }

    public void deleteTank(Long id){
        this.tankReposotory.deleteById(id);

    }

}


package com.tank_service.persistence.repository;

import com.tank_service.persistence.entity.Tank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TankRepository extends JpaRepository<Tank, Long> {
}

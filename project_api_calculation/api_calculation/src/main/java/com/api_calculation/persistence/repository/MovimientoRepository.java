package com.api_calculation.persistence.repository;

import com.api_calculation.persistence.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}

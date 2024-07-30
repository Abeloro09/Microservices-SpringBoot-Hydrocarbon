package com.api_calculation.persistence.repository;

import com.api_calculation.persistence.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidacionRepository extends JpaRepository<Liquidacion, Long> {
}

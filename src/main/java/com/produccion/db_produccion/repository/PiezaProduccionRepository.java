package com.produccion.db_produccion.repository;

import com.produccion.db_produccion.entity.PiezaProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiezaProduccionRepository extends JpaRepository<PiezaProduccion, Long> {
}
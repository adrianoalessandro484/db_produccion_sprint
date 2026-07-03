package com.produccion.db_produccion.repository;

import com.produccion.db_produccion.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByEstadoInOrderByFechaFinalizacionAsc(List<String> estados);
    List<Orden> findByClienteIdOrderByFechaFinalizacionDesc(Long clienteId);
}
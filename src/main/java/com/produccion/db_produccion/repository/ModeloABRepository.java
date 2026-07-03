package com.produccion.db_produccion.repository;

import com.produccion.db_produccion.entity.ModeloAB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloABRepository extends JpaRepository<ModeloAB, Long> {
    List<ModeloAB> findAllByOrderByNombreAsc();
}
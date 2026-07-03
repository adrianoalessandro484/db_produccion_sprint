package com.produccion.db_produccion.repository;

import com.produccion.db_produccion.entity.ModeloPant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloPantRepository extends JpaRepository<ModeloPant, Long> {
    List<ModeloPant> findAllByOrderByNombreAsc();
}
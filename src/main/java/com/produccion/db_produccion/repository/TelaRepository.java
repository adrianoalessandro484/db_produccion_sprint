package com.produccion.db_produccion.repository;

import com.produccion.db_produccion.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {
    List<Tela> findAllByOrderByNombreAsc();
}
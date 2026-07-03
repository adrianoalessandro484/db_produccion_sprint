package com.produccion.db_produccion.service;

import com.produccion.db_produccion.dto.OrdenRequest;
import com.produccion.db_produccion.dto.OrdenResponse;
import com.produccion.db_produccion.dto.PiezaResponse;

import java.util.List;

public interface ProduccionService {
    List<OrdenResponse> findActivas();
    OrdenResponse findById(Long id);
    List<OrdenResponse> findByCliente(Long clienteId);
    OrdenResponse create(OrdenRequest req);
    OrdenResponse updateEstado(Long id, String nuevoEstado);
    PiezaResponse updatePiezaEstado(Long piezaId, String nuevoEstado);
    PiezaResponse asignarPieza(Long piezaId, java.util.UUID encargadoId);
}
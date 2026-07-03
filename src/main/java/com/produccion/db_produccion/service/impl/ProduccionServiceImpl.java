package com.produccion.db_produccion.service.impl;

import com.produccion.db_produccion.dto.OrdenRequest;
import com.produccion.db_produccion.dto.OrdenResponse;
import com.produccion.db_produccion.dto.PiezaResponse;
import com.produccion.db_produccion.entity.Orden;
import com.produccion.db_produccion.entity.PiezaProduccion;
import com.produccion.db_produccion.repository.OrdenRepository;
import com.produccion.db_produccion.repository.PiezaProduccionRepository;
import com.produccion.db_produccion.service.ProduccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProduccionServiceImpl implements ProduccionService {

    private final OrdenRepository ordenRepository;
    private final PiezaProduccionRepository piezaRepository;

    public ProduccionServiceImpl(OrdenRepository ordenRepository, PiezaProduccionRepository piezaRepository) {
        this.ordenRepository = ordenRepository;
        this.piezaRepository = piezaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenResponse> findActivas() {
        return ordenRepository
                .findByEstadoInOrderByFechaFinalizacionAsc(List.of("por cortar", "haciendose", "por corregir"))
                .stream()
                .map(OrdenResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenResponse findById(Long id) {
        return OrdenResponse.fromEntity(ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenResponse> findByCliente(Long clienteId) {
        return ordenRepository.findByClienteIdOrderByFechaFinalizacionDesc(clienteId).stream()
                .map(OrdenResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public OrdenResponse create(OrdenRequest req) {
        Orden orden = Orden.builder()
                .clienteId(req.getClienteId())
                .genero(req.getGenero())
                .telaId(req.getTelaId())
                .nombreTelaImp(req.getNombreTelaImp())
                .codigoColor(req.getCodigoColor())
                .referenciaColor(req.getReferenciaColor())
                .modeloAbId(req.getModeloAbId())
                .modeloPantId(req.getModeloPantId())
                .costoTotal(req.getCostoTotal())
                .costoPagado(BigDecimal.ZERO)
                .fechaInicio(req.getFechaInicio() != null ? req.getFechaInicio() : LocalDate.now())
                .fechaFinalizacion(req.getFechaFinalizacion())
                .estado("por cortar")
                .build();

        Orden saved = ordenRepository.save(orden);

        if (req.getPiezasSeleccionadas() != null) {
            for (String tipo : req.getPiezasSeleccionadas()) {
                PiezaProduccion pieza = PiezaProduccion.builder()
                        .orden(saved)
                        .tipoPieza(tipo)
                        .estadoPieza("sin asignar")
                        .build();
                saved.getPiezas().add(pieza);
            }
            ordenRepository.save(saved);
        }

        return OrdenResponse.fromEntity(saved);
    }

    @Override
    @Transactional
    public OrdenResponse updateEstado(Long id, String nuevoEstado) {
        Orden o = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + id));
        o.setEstado(nuevoEstado);
        return OrdenResponse.fromEntity(ordenRepository.save(o));
    }

    @Override
    @Transactional
    public PiezaResponse updatePiezaEstado(Long piezaId, String nuevoEstado) {
        PiezaProduccion p = piezaRepository.findById(piezaId)
                .orElseThrow(() -> new RuntimeException("Pieza no encontrada: " + piezaId));
        p.setEstadoPieza(nuevoEstado);
        return PiezaResponse.fromEntity(piezaRepository.save(p));
    }

    @Override
    @Transactional
    public PiezaResponse asignarPieza(Long piezaId, UUID encargadoId) {
        PiezaProduccion p = piezaRepository.findById(piezaId)
                .orElseThrow(() -> new RuntimeException("Pieza no encontrada: " + piezaId));
        p.setEncargadoId(encargadoId);
        if ("sin asignar".equals(p.getEstadoPieza())) {
            p.setEstadoPieza("haciendo");
        }
        return PiezaResponse.fromEntity(piezaRepository.save(p));
    }
}
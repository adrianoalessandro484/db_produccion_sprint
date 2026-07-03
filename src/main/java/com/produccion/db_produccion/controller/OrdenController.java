package com.produccion.db_produccion.controller;

import com.produccion.db_produccion.dto.OrdenRequest;
import com.produccion.db_produccion.dto.OrdenResponse;
import com.produccion.db_produccion.dto.PiezaResponse;
import com.produccion.db_produccion.service.ProduccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class OrdenController {

    private final ProduccionService service;

    public OrdenController(ProduccionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrdenResponse>> list(
            @RequestParam(required = false, defaultValue = "activas") String filtro,
            @RequestParam(required = false) Long clienteId) {
        if (clienteId != null) return ResponseEntity.ok(service.findByCliente(clienteId));
        if ("activas".equals(filtro)) return ResponseEntity.ok(service.findActivas());
        return ResponseEntity.ok(service.findActivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrdenResponse> create(@RequestBody OrdenRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenResponse> updateEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.updateEstado(id, body.get("estado")));
    }

    @PatchMapping("/piezas/{piezaId}/estado")
    public ResponseEntity<PiezaResponse> updatePiezaEstado(@PathVariable Long piezaId, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.updatePiezaEstado(piezaId, body.get("estado")));
    }

    @PatchMapping("/piezas/{piezaId}/asignar")
    public ResponseEntity<PiezaResponse> asignar(@PathVariable Long piezaId, @RequestBody Map<String, String> body) {
        UUID encargadoId = UUID.fromString(body.get("encargadoId"));
        return ResponseEntity.ok(service.asignarPieza(piezaId, encargadoId));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "db_produccion"));
    }
}
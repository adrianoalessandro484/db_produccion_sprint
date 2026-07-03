package com.produccion.db_produccion.controller;

import com.produccion.db_produccion.entity.ModeloAB;
import com.produccion.db_produccion.entity.ModeloPant;
import com.produccion.db_produccion.entity.Tela;
import com.produccion.db_produccion.repository.ModeloABRepository;
import com.produccion.db_produccion.repository.ModeloPantRepository;
import com.produccion.db_produccion.repository.TelaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@CrossOrigin(origins = "*")
public class CatalogoController {

    private final TelaRepository telaRepo;
    private final ModeloABRepository modeloAbRepo;
    private final ModeloPantRepository modeloPantRepo;

    public CatalogoController(TelaRepository telaRepo, ModeloABRepository modeloAbRepo, ModeloPantRepository modeloPantRepo) {
        this.telaRepo = telaRepo;
        this.modeloAbRepo = modeloAbRepo;
        this.modeloPantRepo = modeloPantRepo;
    }

    @GetMapping("/telas")
    public ResponseEntity<List<Tela>> telas() {
        return ResponseEntity.ok(telaRepo.findAllByOrderByNombreAsc());
    }

    @PostMapping("/telas")
    public ResponseEntity<Tela> crearTela(@RequestBody Tela t) {
        return ResponseEntity.ok(telaRepo.save(t));
    }

    @GetMapping("/modelos-ab")
    public ResponseEntity<List<ModeloAB>> modelosAb() {
        return ResponseEntity.ok(modeloAbRepo.findAllByOrderByNombreAsc());
    }

    @PostMapping("/modelos-ab")
    public ResponseEntity<ModeloAB> crearModeloAb(@RequestBody ModeloAB m) {
        return ResponseEntity.ok(modeloAbRepo.save(m));
    }

    @GetMapping("/modelos-pant")
    public ResponseEntity<List<ModeloPant>> modelosPant() {
        return ResponseEntity.ok(modeloPantRepo.findAllByOrderByNombreAsc());
    }

    @PostMapping("/modelos-pant")
    public ResponseEntity<ModeloPant> crearModeloPant(@RequestBody ModeloPant m) {
        return ResponseEntity.ok(modeloPantRepo.save(m));
    }
}
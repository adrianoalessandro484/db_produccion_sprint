package com.produccion.db_produccion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(nullable = false, length = 10)
    private String genero;

    @Column(name = "tela_id")
    private Long telaId;

    @Column(name = "nombre_tela_imp", length = 100)
    private String nombreTelaImp;

    @Column(name = "codigo_color", length = 50)
    private String codigoColor;

    @Column(name = "referencia_color", length = 50)
    private String referenciaColor;

    @Column(name = "modelo_ab_id")
    private Long modeloAbId;

    @Column(name = "modelo_pant_id")
    private Long modeloPantId;

    @Column(name = "costo_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "costo_pagado", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoPagado = BigDecimal.ZERO;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_finalizacion", nullable = false)
    private LocalDate fechaFinalizacion;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PiezaProduccion> piezas = new ArrayList<>();
}
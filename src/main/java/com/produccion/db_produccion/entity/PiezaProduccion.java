package com.produccion.db_produccion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "piezas_produccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PiezaProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;

    @Column(name = "tipo_pieza", nullable = false, length = 20)
    private String tipoPieza;

    @Column(name = "estado_pieza", nullable = false, length = 20)
    private String estadoPieza;

    @Column(name = "encargado_id", columnDefinition = "uuid")
    private UUID encargadoId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
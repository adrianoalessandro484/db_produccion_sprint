package com.produccion.db_produccion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modelos_ab")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModeloAB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
}
package com.produccion.db_produccion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
}
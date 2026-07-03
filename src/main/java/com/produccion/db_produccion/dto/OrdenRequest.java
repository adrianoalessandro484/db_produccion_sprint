package com.produccion.db_produccion.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenRequest {
    private Long clienteId;
    private String genero;
    private Long telaId;
    private String nombreTelaImp;
    private String codigoColor;
    private String referenciaColor;
    private Long modeloAbId;
    private Long modeloPantId;
    private BigDecimal costoTotal;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private List<String> piezasSeleccionadas;
}
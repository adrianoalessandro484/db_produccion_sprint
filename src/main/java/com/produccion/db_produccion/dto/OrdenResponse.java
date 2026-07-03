package com.produccion.db_produccion.dto;

import com.produccion.db_produccion.entity.Orden;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenResponse {
    private Long id;
    private Long clienteId;
    private String genero;
    private Long telaId;
    private String nombreTelaImp;
    private String codigoColor;
    private String referenciaColor;
    private Long modeloAbId;
    private Long modeloPantId;
    private BigDecimal costoTotal;
    private BigDecimal costoPagado;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private String estado;
    private LocalDateTime createdAt;
    private List<PiezaResponse> piezas;

    public static OrdenResponse fromEntity(Orden o) {
        return OrdenResponse.builder()
                .id(o.getId())
                .clienteId(o.getClienteId())
                .genero(o.getGenero())
                .telaId(o.getTelaId())
                .nombreTelaImp(o.getNombreTelaImp())
                .codigoColor(o.getCodigoColor())
                .referenciaColor(o.getReferenciaColor())
                .modeloAbId(o.getModeloAbId())
                .modeloPantId(o.getModeloPantId())
                .costoTotal(o.getCostoTotal())
                .costoPagado(o.getCostoPagado())
                .fechaInicio(o.getFechaInicio())
                .fechaFinalizacion(o.getFechaFinalizacion())
                .estado(o.getEstado())
                .createdAt(o.getCreatedAt())
                .piezas(o.getPiezas().stream().map(PiezaResponse::fromEntity).toList())
                .build();
    }
}
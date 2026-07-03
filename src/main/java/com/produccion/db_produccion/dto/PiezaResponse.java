package com.produccion.db_produccion.dto;

import com.produccion.db_produccion.entity.PiezaProduccion;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PiezaResponse {
    private Long id;
    private Long ordenId;
    private String tipoPieza;
    private String estadoPieza;
    private UUID encargadoId;

    public static PiezaResponse fromEntity(PiezaProduccion p) {
        return PiezaResponse.builder()
                .id(p.getId())
                .ordenId(p.getOrden() != null ? p.getOrden().getId() : null)
                .tipoPieza(p.getTipoPieza())
                .estadoPieza(p.getEstadoPieza())
                .encargadoId(p.getEncargadoId())
                .build();
    }
}
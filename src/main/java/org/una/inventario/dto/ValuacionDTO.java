package org.una.inventario.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValuacionDTO {

    private ActivoDTO activo;
    private InventarioDTO inventario;
    private double precioValuacion;
    private Date fechaCreacion;
}

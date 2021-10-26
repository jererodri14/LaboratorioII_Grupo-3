package org.una.inventario.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransaccionesDTO {

    private Long id;
    private String objeto;
    private String accion;
    private Date fechaCreacion;
}

package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivoAsignadoDTO {

    private Long id;
    private UsuarioDTO usuario;
    private ActivoDTO activo;
    private String justificacion;
    private String estado;
    private Date fechaCreacion;
}

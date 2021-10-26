package org.una.inventario.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private DepartamentoDTO departamento;
    private boolean esJefe;
    private String passwordEncriptado;
    private RolDTO rol;

}


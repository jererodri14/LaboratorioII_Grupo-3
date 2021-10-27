package org.una.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParametrosDTO {
    private Long id;
    private String valor;
    private String nombre;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
}

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
public class AlertaDTO{
    private Long id;
    private String tipo;
    private String descripcion;
    private boolean estado;
    private Long responsable;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private ActivoDTO activo;
}

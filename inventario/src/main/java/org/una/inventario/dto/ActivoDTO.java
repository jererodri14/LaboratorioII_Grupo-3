package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivoDTO {

    private Long id;
    private CategoriaDTO categoria;
    private MarcaDTO marca;
    private ProveedorDTO proveedor;
    private Long continente;
    private String nombre;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
}

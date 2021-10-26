package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoriaDTO {

    private Long id;
    private String nombre;
    private String estado;
    private Date fechaCreacion;

}

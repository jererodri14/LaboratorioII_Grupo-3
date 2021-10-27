package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarcaDTO {

    private Long id;
    private String nombre;
    private boolean estado;
    private Date fechaCreacion;
}

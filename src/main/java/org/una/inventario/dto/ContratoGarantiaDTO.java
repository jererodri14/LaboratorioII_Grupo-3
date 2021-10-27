package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContratoGarantiaDTO {

    private Long id;
    private Double montoAsegurado;
    private Double costo;
    private boolean estado;
    private String fechaVencimiento;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private ActivoDTO activo;

}

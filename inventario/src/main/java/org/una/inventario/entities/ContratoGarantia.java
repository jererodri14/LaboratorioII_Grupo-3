package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contratosGarantias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContratoGarantia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto_asegurado")
    private Double montoAsegurado;

    @Column(name = "costo")
    private Double costo;

    @Column(length = 10)
    private String estado;

    @Column(name = "fecha_vencimiento", length = 100)
    private Double fechaVencimiento;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "activosId")
    private Activo activo;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado="activo";
        fechaCreacion = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}

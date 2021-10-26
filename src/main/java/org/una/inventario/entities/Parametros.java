package org.una.inventario.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "parametros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Parametros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", length = 250)
    private String valor;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "estado", length = 10)
    private String estado;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

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

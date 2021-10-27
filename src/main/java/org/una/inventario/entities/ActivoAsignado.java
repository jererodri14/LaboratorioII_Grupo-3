package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activosAsignados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivoAsignado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "justificacion", length = 100)
    private String justificacion;

    @Column(name = "estado", length = 10)
    private boolean estado;

    @Column
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuariosId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "activosId")
    private Activo activo;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado = true;
        fechaCreacion = new Date();
    }

}

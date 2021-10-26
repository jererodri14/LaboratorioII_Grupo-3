package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "transacciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transacciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "objeto", length = 50)
    private String objeto;

    @ManyToOne
    @JoinColumn(name="Usuarios_id")
    private Usuario usuarioId;

    @Column(name = "accion", length = 50)
    private String accion;

    @Column(name = "fecha_creacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name="rol_id")
    private Rol rolId;
}

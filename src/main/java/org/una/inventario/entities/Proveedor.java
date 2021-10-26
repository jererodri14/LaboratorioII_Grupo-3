package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "notas", length = 250)
    private String notas;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "correo_electronico", length = 100)
    private String correoElectronico;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Activo> activos = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado = "activo";
        fechaCreacion = new Date();
    }
}

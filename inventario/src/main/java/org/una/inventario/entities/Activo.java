package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "continente", length = 100)
    private Long continente;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "activo")
    @PrimaryKeyJoinColumn
    private Valuacion valuacion;

    @ManyToOne
    @JoinColumn(name = "categoriasId")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marcasId")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "proveedoresId")
    private Proveedor proveedor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<ContratoGarantia> contratoGarantia = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<ActivoAsignado> activoAsignado = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<Alerta> alerta = new ArrayList<>();

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

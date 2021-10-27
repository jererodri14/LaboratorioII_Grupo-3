package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Parametros;
import org.una.inventario.entities.Proveedor;

import java.util.List;

@Repository
public interface IProveedorRepository  extends JpaRepository<Proveedor, Long> {
    public List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
    public Proveedor findByCorreoElectronico(String correoElectronico);
    public List<Proveedor> findByEstado(boolean estado);
}

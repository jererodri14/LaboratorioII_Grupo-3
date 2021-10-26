package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Proveedor;

@Repository
public interface IProveedorRepository  extends JpaRepository<Proveedor, Long> {

}

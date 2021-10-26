package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Inventario;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Long> {

}

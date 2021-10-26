package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{


}

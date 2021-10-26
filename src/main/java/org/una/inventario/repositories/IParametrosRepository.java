package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Parametros;

@Repository
public interface IParametrosRepository  extends JpaRepository<Parametros, Long> {
}

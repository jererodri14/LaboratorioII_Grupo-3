package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Parametros;

import java.util.List;

@Repository
public interface IParametrosRepository  extends JpaRepository<Parametros, Long> {

    public List<Parametros> findByNombreContainingIgnoreCase(String nombre);
}

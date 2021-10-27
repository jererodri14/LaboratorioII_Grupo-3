package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.Query;
import org.una.inventario.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long>{

    public List<Departamento> findByEstado(boolean estado);


}

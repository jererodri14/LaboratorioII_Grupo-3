package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Alerta;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, Long> {
    public List<Alerta> findByTipo(String tipo);

    public List<Alerta> findByEstado(String estado);

    public List<Alerta> findByResponsable(Long responsable);

}

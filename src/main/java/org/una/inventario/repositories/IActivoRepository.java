package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;
import java.util.List;

@Repository
public interface IActivoRepository  extends JpaRepository<Activo, Long> {

    public List<Activo> findByCategoriaId(Long id);

    public List<Activo> findByProveedorId(Long id);

    public List<Activo> findByMarcaId(Long id);

}

package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Transacciones;
import java.util.Date;
import java.util.List;

@Repository
public interface ITransaccionesRepository extends JpaRepository<Transacciones,Long> {

    public List<Transacciones> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date startDate, Date endDate);

    public List<Transacciones> findByRolIdAndFechaCreacionBetween (Long rolId, Date startDate, Date endDate);

    public List<Transacciones> findByObjetoAndFechaCreacionBetween(String objetoId, Date startDate, Date endDate);

    public List<Transacciones> findByFechaCreacionBetween(Date startDate, Date endDate);
}

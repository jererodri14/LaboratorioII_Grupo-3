package org.una.inventario.services;

import org.una.inventario.dto.TransaccionesDTO;

import java.util.Optional;
import java.util.Date;
import java.util.List;

public interface ITransaccionesService {

        public Optional<TransaccionesDTO> findById(Long id);

        public Optional<List<TransaccionesDTO>> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date startDate, Date endDate);

        public Optional<List<TransaccionesDTO>> findByRolIdAndFechaCreacionBetween(Long rolId, Date startDate, Date endDate);

        public Optional<List<TransaccionesDTO>> findByObjetoAndFechaCreacionBetween(String objetoId, Date startDate, Date endDate);

        public Optional<List<TransaccionesDTO>> findByFechaCreacionBetween(Date startDate, Date endDate);

        public Optional create(TransaccionesDTO transaccionesDTO);

        public Optional<TransaccionesDTO> update(TransaccionesDTO transaccionesDTO, Long id);

        public void delete(Long id);

        public void deleteAll();
}

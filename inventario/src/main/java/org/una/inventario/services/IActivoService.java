package org.una.inventario.services;

import org.una.inventario.dto.ActivoDTO;

import java.util.List;
import java.util.Optional;

public interface IActivoService {

    public Optional<List<ActivoDTO>> findAll();

    public Optional<ActivoDTO> findById(Long id);

    public Optional<List<ActivoDTO>> findByCategoriaId(Long id);

    public Optional<List<ActivoDTO>> findByProveedorId(Long id);

    public Optional<List<ActivoDTO>> findByMarcaId(Long id);

    public Optional<ActivoDTO> create(ActivoDTO activoDTO);

    public Optional<ActivoDTO> update (ActivoDTO activoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}

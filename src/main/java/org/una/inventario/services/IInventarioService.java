package org.una.inventario.services;

import org.una.inventario.dto.InventarioDTO;
import java.util.List;
import java.util.Optional;

public interface IInventarioService {

    public Optional<List<InventarioDTO>> findAll();

    public Optional<InventarioDTO> findById(Long id);

    public Optional<InventarioDTO> create(InventarioDTO inventarioDTO);

    public Optional<InventarioDTO> update(InventarioDTO inventarioDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}

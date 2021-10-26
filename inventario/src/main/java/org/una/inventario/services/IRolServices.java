package org.una.inventario.services;

import org.una.inventario.dto.RolDTO;
import java.util.Optional;
import java.util.Date;
import java.util.List;

public interface IRolServices {

    public Optional<RolDTO> findById(Long id);

    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> create(RolDTO rolDTO);

    public Optional<RolDTO> update(RolDTO rolDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}

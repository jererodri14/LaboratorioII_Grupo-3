package org.una.inventario.services;

import org.springframework.stereotype.Service;
import org.una.inventario.dto.DepartamentoDTO;
import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {

    public Optional<List<DepartamentoDTO>> findAll();

    public Optional<DepartamentoDTO> findById(Long id);

    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado);

    public Optional<DepartamentoDTO> create(DepartamentoDTO departamentoDTO);

    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}

package org.una.inventario.services;

import org.springframework.security.access.method.P;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IParametrosService {

    public Optional<List<ParametrosDTO>> findAll();

    public Optional<ParametrosDTO> findById(Long id);

    public Optional<List<ParametrosDTO>> findByNombreAproximateIgnoreCase(String nombre);

    public Optional<ParametrosDTO> create(ParametrosDTO parametrosDTO);

    public Optional<ParametrosDTO> update(ParametrosDTO parametrosDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}

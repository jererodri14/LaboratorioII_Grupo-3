package org.una.inventario.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public Optional<List<UsuarioDTO>> findByDepartamentoId (Long id);

    public Optional<UsuarioDTO> create(UsuarioDTO usuarioDTO);

    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id);

    public UserDetails loadUserByUsername(String username);

    public void delete(Long id);

    public void deleteAll();

}


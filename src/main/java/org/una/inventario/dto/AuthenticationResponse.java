package org.una.inventario.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {

    private String jwt;
    private UsuarioDTO usuarioDTO;
    private RolDTO rolDTO;

}

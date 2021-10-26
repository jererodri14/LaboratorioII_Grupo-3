package org.una.inventario.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationRequest {

    private String cedula;
    private String password;

}

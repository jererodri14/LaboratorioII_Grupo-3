package org.una.inventario.services;

import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.AuthenticationResponse;

public interface IAuthenticationService {

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}

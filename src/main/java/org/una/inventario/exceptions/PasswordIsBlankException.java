package org.una.inventario.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Component
public class PasswordIsBlankException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final HttpStatus errorCode= HttpStatus.NOT_IMPLEMENTED;

    private final  String errorMessage= "La contraseña está vacía, inténtelo nuevamente";

}

package org.una.inventario.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Component
public class MissingInputsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final HttpStatus errorCode= HttpStatus.BAD_REQUEST;

    private final  String errorMessage= "Información incompleta, inténtelo nuevamente";


}

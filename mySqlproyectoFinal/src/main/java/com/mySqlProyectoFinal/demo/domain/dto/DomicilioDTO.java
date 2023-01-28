package com.mySqlProyectoFinal.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomicilioDTO {
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

}

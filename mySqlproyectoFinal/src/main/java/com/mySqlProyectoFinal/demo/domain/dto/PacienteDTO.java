package com.mySqlProyectoFinal.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PacienteDTO {
    @NotBlank( message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El campo email no puede estar vacio")
    private  String email;

    @NotBlank (message = "El campo DNI no puede estar vacio")


    private DomicilioDTO domicilio;

}

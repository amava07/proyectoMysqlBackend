package com.mySqlProyectoFinal.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class OdontologoDTO {
    @NotBlank(message = "El campo Matricula no puede estar vacio")
    private Long matricula;

    @NotBlank(message = "El campo Nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    private String apellido;


}

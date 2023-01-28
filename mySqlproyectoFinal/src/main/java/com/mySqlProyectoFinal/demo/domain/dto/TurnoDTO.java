package com.mySqlProyectoFinal.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TurnoDTO {
    @NotBlank ( message = "El campo paciente no puede estar vacio")
    private Long pacienteId;

    @NotBlank (message = "El campo odontologo no puede estar vacio")
    private Long odontologoId;

    private String date;

    private String hour;
}

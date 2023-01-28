package com.mySqlProyectoFinal.demo.domain.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table ( name = "domicilios")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Domicilio {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "domicilio_id")
    private Long id;

    @NotNull(message = "El campo Calle no debe estar vacio");
    private String calle;

    @NotNull(message = "El campo Número no debe estar vacio");
    private String numero;

    @NotNull(message = "El campo Localidad no debe estar vacio");
    private String localidad;

    @NotNull ( message = "El campo Provincia no debe estar vacio");
    private String provincia;

    private  String direccionCompleta = "Calle " + this.calle+ "#"+ this.numero+"\n"+ this.provincia;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "domicilio")
    private Set<Paciente> pacientes;

}

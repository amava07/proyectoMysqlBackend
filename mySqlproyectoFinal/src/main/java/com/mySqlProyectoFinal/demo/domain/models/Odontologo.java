package com.mySqlProyectoFinal.demo.domain.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "odontologos")
@SQLDelete( sql = "UPDATE odontologos SET deleted = true WHERE odontologo_id =?")
@Where(clause = "deleted = false")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")


public class Odontologo {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "odontologo_id")
    private Long id;

    @NotNull(message = "El campo Matricula no debe estar vacio")
    private Long matricula;

    @NotNull ( message = "El campo Nombre no debe estar vacio")
    private String nombre;

    @NotNull ( message = "El campo Apellido no debe estar vacio")
    private String apellido;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "odontologo")
    private Set<Turno> turnos;

    private boolean deleted = Boolean.FALSE;


}

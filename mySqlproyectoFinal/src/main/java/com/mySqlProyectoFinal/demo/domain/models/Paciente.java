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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table ( name = "pacientes")
@SQLDelete( sql = "UPDATE = pacientes SET deleted = true WHERE paciente_id = ?")
@Where(clause = "deleted = false")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Paciente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column ( name = "paciente_id")
    private Long id;

    @NotNull (message = "El campo Nombre no debe estar vacío")
    private  String nombre;

    @NotNull( message = "El campo Apellido no debe estar vacio")
    private String apellido;

    @NotNull ( message = "El campo Email no debe estar vacio ")
    private String email;

    @NotNull(message = "El campo DNI no debe estar vacio ")
    private Long dni;

    private LocalDateTime fechaIngreso = LocalDateTime.now(ZoneId.of("-07;00"));

    @ManyToOne
    @NotNull ( message = "El campo Domicilo no debe estar vacio")
    @JoinColumn ( name = "domicilio_id")
    private Domicilio domicilio;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "paciente")
    private Set<Turno> turnos;

    private boolean deleted = Boolean.FALSE;

}

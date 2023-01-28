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

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "turnos")
@SQLDelete( sql = "UPDATE turnos SET deleted = true WHERE turno_id = ?")
@Where( clause = "deleted = false")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Turno {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "turno_id")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "odontologo_id")
    private Odontologo odontologo;
    private LocalDateTime date;
    private LocalDateTime created = LocalDateTime.now(ZoneId.of("-07:00"));
    private boolean deleted = Boolean.FALSE;
}

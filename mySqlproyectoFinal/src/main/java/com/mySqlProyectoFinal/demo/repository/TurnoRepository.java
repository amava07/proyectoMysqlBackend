package com.mySqlProyectoFinal.demo.repository;

import com.mySqlProyectoFinal.demo.domain.models.Odontologo;
import com.mySqlProyectoFinal.demo.domain.models.Paciente;
import com.mySqlProyectoFinal.demo.domain.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno,Long> {
    List<Turno> findByOdontologoOrPaciente (Odontologo odontologo, Paciente paciente);
}

package com.mySqlProyectoFinal.demo.repository;


import com.mySqlProyectoFinal.demo.domain.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByDni (Long dni);
}

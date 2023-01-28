package com.mySqlProyectoFinal.demo.repository;

import com.mySqlProyectoFinal.demo.domain.dto.OdontologoDTO;
import com.mySqlProyectoFinal.demo.domain.models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Odontologo findByMatricula (Long matricula);
}

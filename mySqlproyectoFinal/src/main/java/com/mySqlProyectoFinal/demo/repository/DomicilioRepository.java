package com.mySqlProyectoFinal.demo.repository;

import com.mySqlProyectoFinal.demo.domain.models.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio,Long>{
}

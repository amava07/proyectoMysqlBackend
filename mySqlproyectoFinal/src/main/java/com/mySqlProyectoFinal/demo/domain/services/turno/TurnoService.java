package com.mySqlProyectoFinal.demo.domain.services.turno;

import com.mySqlProyectoFinal.demo.domain.dto.TurnoDTO;
import com.mySqlProyectoFinal.demo.domain.dto.TurnosAsignadosDTO;

import java.text.ParseException;
import java.util.List;

public interface TurnoService {
    TurnosAsignadosDTO saveTurno (TurnoDTO turnoDTO) throws ParseException;

    List<TurnosAsignadosDTO> findAll();

    TurnosAsignadosDTO findById (Long id);

    boolean deleteTurno (Long id);
}

package com.mySqlProyectoFinal.demo.domain.services.paciente;

import com.mySqlProyectoFinal.demo.domain.dto.PacienteDTO;

import java.util.List;
import java.util.Map;

public interface PacienteService {
    PacienteDTO savePaciente ( PacienteDTO pacienteDTO);
    List<PacienteDTO> findAll();

    PacienteDTO findById(Long id);

    PacienteDTO findByDni(Long dni);

    boolean deletePaciente(Long id);

    boolean updatePaciente (Map<String,Object> partialUpdate, Long id);


}

package com.mySqlProyectoFinal.demo.domain.services.odontologo;

import com.mySqlProyectoFinal.demo.domain.dto.OdontologoDTO;
import com.mySqlProyectoFinal.demo.domain.models.Odontologo;

import java.util.List;
import java.util.Map;

public interface OdontologoService {
    OdontologoDTO saveOdontologo(OdontologoDTO odontologoDTO);
    List<OdontologoDTO> findAll();

    OdontologoDTO findById (Long id);

    OdontologoDTO findByMatricula(Long matricula);

    boolean deleteOdontologo(Long id);
    boolean UpdateOdontologo (Map<String,Object> partialUpdate, Long id);
}

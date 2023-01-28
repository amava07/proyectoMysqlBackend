package com.mySqlProyectoFinal.demo.domain.services.odontologo.impl;

import com.mySqlProyectoFinal.demo.domain.dto.OdontologoDTO;
import com.mySqlProyectoFinal.demo.domain.models.Odontologo;
import com.mySqlProyectoFinal.demo.domain.models.Turno;
import com.mySqlProyectoFinal.demo.domain.services.odontologo.OdontologoService;
import com.mySqlProyectoFinal.demo.repository.OdontologoRepository;
import com.mySqlProyectoFinal.demo.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoImpl implements OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private ModelMapper modelMapper;

    final Logger LOGGER = Logger.getLogger(OdontologoImpl.class);


    @Override
    public OdontologoDTO saveOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = modelMapper.map(odontologoDTO, Odontologo.class);
        return modelMapper.map (odontologoRepository.save(odontologo),OdontologoDTO.class);
    }

    @Override
    public List<OdontologoDTO> findAll() {
        return odontologoRepository.findAll().stream().map(odontologo -> modelMapper.map(odontologo,OdontologoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public OdontologoDTO findById(Long id) {
        return modelMapper.map(odontologoRepository.findById(id),OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO findByMatricula(Long matricula) {
        if (odontologoRepository.findByMatricula(matricula) == null){
            LOGGER.info( "No se encontró ningún odontologo con la matricula ingresada");
            return null;
        } else {
            return modelMapper.map(odontologoRepository.findByMatricula(matricula),OdontologoDTO.class);
        }
    }

    @Override
    public boolean deleteOdontologo(Long id) {
        List<Turno> turnos = turnoRepository.findByOdontologoOrPaciente(odontologoRepository.findById(id).get(),null);
        if (!odontologoRepository.findById(id).isEmpty() && turnos.isEmpty()){
            odontologoRepository.deleteById(id);
            LOGGER.info("Se ha eliminado corretamente Odontologo");
            return true;
        } else {
            LOGGER.error("Verifique si el Odontologo existe, también verifique que no tiene turnos asignados");
            return false;
        }
    }

    @Override
    public boolean UpdateOdontologo(Map<String, Object> partialUpdate, Long id) {
        Optional<Odontologo> odontologoFound = odontologoRepository.findById(id);
        return odontologoFound.map(odontologo -> {
            partialUpdate.forEach((key,value) -> {
               if (key.equals("matricula")){
                    if (value instanceof Integer){
                        Long newValue = Long.valueOf((Integer)value);
                        odontologo.setMatricula(newValue);
                    } else if (value instanceof Long) {
                           odontologo.setMatricula((Long) value);
                       }else{
                           throw new RuntimeException("El tipo de valor ingresado no es válido");

                       }
               } else {
                       Field field = ReflectionUtils.findField(odontologo.getClass(), key);
                       assert field != null;
                       field.setAccessible(true);
                       ReflectionUtils.setField(field, odontologo, value);
                   }
            });
            odontologoRepository.save(odontologo);
            LOGGER.info("Se ha actualizado correctamente el odontologo");
            return true;
        }) .orElse ( false);
    }
}

package com.mySqlProyectoFinal.demo.domain.services.paciente.impl;

import com.mySqlProyectoFinal.demo.domain.dto.DomicilioDTO;
import com.mySqlProyectoFinal.demo.domain.dto.PacienteDTO;
import com.mySqlProyectoFinal.demo.domain.models.Domicilio;
import com.mySqlProyectoFinal.demo.domain.models.Paciente;
import com.mySqlProyectoFinal.demo.domain.models.Turno;
import com.mySqlProyectoFinal.demo.domain.services.paciente.PacienteService;
import com.mySqlProyectoFinal.demo.repository.DomicilioRepository;
import com.mySqlProyectoFinal.demo.repository.PacienteRepository;
import com.mySqlProyectoFinal.demo.repository.TurnoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class PacienteImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    final Logger LOGGER = Logger.getLogger(PacienteImpl.class);


    @Override
    public PacienteDTO savePaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.dto2Model(pacienteDTO);
        return modelMapper.map(pacienteRepository.save(paciente),PacienteDTO.class);
    }

    @Override
    public List<PacienteDTO> findAll() {
        return pacienteRepository.findAll().stream().map(paciente -> modelMapper.map (paciente,PacienteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PacienteDTO findById(Long id) {
        return modelMapper.map(pacienteRepository.findById(id),PacienteDTO.class);
    }

    @Override
    public PacienteDTO findByDni(Long dni) {
        if(pacienteRepository.findByDni(dni) == null) {
            LOGGER.info("No se encontro el Paciente con el DNI");
            return null;
        } else {
            return modelMapper.map(pacienteRepository.findByDni(dni), PacienteDTO.class);
        }
    }

    @Override
    public boolean deletePaciente(Long id) {
        List<Turno> turnos = turnoRepository.findByOdontologoOrPaciente(null,pacienteRepository.findById(id).get());
        if(!pacienteRepository.findById(id).isEmpty() && turnos.isEmpty()){
            pacienteRepository.deleteById(id);
            LOGGER.info("Se ha eliminado correctamente el paciente");
            return true;
        } else {
            LOGGER.error("verifique si el paciente existe y no tenga citas asignadas");
            return  false;
        }
    }

    @Override
    public boolean updatePaciente(Map<String, Object> partialUpdate, Long id) {
        Optional<Paciente> pacienteFound = pacienteRepository.findById(id);
        return pacienteFound.map(paciente -> {
            partialUpdate.forEach((key, value) -> {
                if (key.equals("domicilio")){
                    Domicilio domicilio = modelMapper.map(value,Domicilio.class);
                    Domicilio domicilioSaved = domicilioRepository.save(domicilio);
                    paciente.setDomicilio(domicilioSaved);
                } else if (key.equals("dni")){
                    if (value instanceof Integer){
                        Long newValue = Long.valueOf((Integer) value);
                        paciente.setDni((Long) value);
                    } else {
                        throw new RuntimeException("El tipo de valor ingresado no es válido ");
                    }
                } else {
                    Field field = ReflectionUtils.findField(paciente.getClass(),key);
                    assert field != null;
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,paciente,value);
                }
            } );
            pacienteRepository.save(paciente);
            LOGGER.info("Se ha actualizado correctamente el paciente");
            return true;
        }).orElse(false);
    }
}

package com.mySqlProyectoFinal.demo.domain.services.turno.impl;

import com.mySqlProyectoFinal.demo.domain.dto.TurnoDTO;
import com.mySqlProyectoFinal.demo.domain.dto.TurnosAsignadosDTO;
import com.mySqlProyectoFinal.demo.domain.models.Odontologo;
import com.mySqlProyectoFinal.demo.domain.models.Paciente;
import com.mySqlProyectoFinal.demo.domain.models.Turno;
import com.mySqlProyectoFinal.demo.domain.services.paciente.impl.PacienteImpl;
import com.mySqlProyectoFinal.demo.domain.services.turno.TurnoService;
import com.mySqlProyectoFinal.demo.repository.OdontologoRepository;
import com.mySqlProyectoFinal.demo.repository.PacienteRepository;
import com.mySqlProyectoFinal.demo.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurnoImpl implements TurnoService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    final Logger LOGGER = Logger.getLogger(PacienteImpl.class);


    @Override
    public TurnosAsignadosDTO saveTurno(TurnoDTO turnoDTO) throws ParseException {
        Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId()).orElse(null);
        Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId()).orElse(null);
        Turno turno = new Turno();

        String fullDate = turnoDTO.getDate() + " " + turnoDTO.getHour();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd-MM-yyyy HH:mm ");
        LocalDateTime dateTime = LocalDateTime.parse(fullDate, formatter);
        turno.setDate(dateTime);

        if( paciente != null && odontologo != null ){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
        }
        return modelMapper.map(turnoRepository.save(turno),TurnosAsignadosDTO.class);
    }

    @Override
    public List<TurnosAsignadosDTO> findAll() {
        return turnoRepository.findAll().stream().map(turno -> modelMapper.map(turno, TurnosAsignadosDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TurnosAsignadosDTO findById(Long id) {
        return modelMapper.map(turnoRepository.findById(id),TurnosAsignadosDTO.class);
    }

    @Override
    public boolean deleteTurno(Long id) {
        if (!turnoRepository.findById(id).isEmpty()){
            turnoRepository.deleteById(id);
            LOGGER.info("La actualización del turno fue correcta");
            return true;
        }else {
            LOGGER.error("Ocurrió un error ");
            return false;
        }
    }
}

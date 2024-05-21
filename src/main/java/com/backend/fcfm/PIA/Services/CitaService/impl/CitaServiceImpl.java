package com.backend.fcfm.PIA.Services.CitaService.impl;

import com.backend.fcfm.PIA.Entity.Cita;
import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Entity.Paciente;
import com.backend.fcfm.PIA.Model.Cita.CitaCreationDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaUpdateDTO;
import com.backend.fcfm.PIA.Repository.CitaRepository.CitaRepository;
import com.backend.fcfm.PIA.Repository.DoctorRepository.DoctorRepository;
import com.backend.fcfm.PIA.Repository.PacienteRepository.PacienteRepository;
import com.backend.fcfm.PIA.Services.CitaService.CitaService;
import com.backend.fcfm.PIA.Utils.CitaMapper;
import com.backend.fcfm.PIA.Utils.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.backend.fcfm.PIA.Model.Cita.CitaEspecification.citaEnFecha;
import static com.backend.fcfm.PIA.Model.Cita.CitaEspecification.fechaEnRango;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final DoctorRepository doctorRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public CitaServiceImpl(CitaRepository citaRepository, DoctorRepository doctorRepository, PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public String crearCita(CitaCreationDTO citaDTO, BindingResult result, Long pacienteId) throws ParseException {

        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(". \n"));
            return errors.toString();
        }

        Cita cita = CitaMapper.toCita(citaDTO);

        Specification<Cita> filtros = Specification.where(citaEnFecha(cita.getFechaCita(), citaDTO.getDoctorId()));
        List<Cita> listCitas = citaRepository.findAll(filtros);
        if (listCitas.size() != 0) {
            return "La fecha esta ocupada por otra cita.";
        }

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        Optional<Doctor> doctorOptional = doctorRepository.findById(citaDTO.getDoctorId());

        if (pacienteOptional.isPresent() && doctorOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            Doctor doctor = doctorOptional.get();

            cita.setDoctor(doctor);
            cita.setPaciente(paciente);
            cita.setIsActive(true);
            citaRepository.save(cita);

            return "Cita creada con éxito.";
        } else {
            return "El doctor o el paciente no existen, contacte MediSite servicio al cliente.";
        }
    }

    @Override
    public String listarCitas(String fechaInicioStr, String fechaFinStr, Long pacienteId) {

        try {
            Date fechaInicio = null;
            Date fechaFin = null;

            if (fechaInicioStr != null && fechaFinStr != null) {
                fechaInicio = DateMapper.parseDate(fechaInicioStr);
                fechaFin = DateMapper.parseDate(fechaFinStr);
            }

            Specification<Cita> filtros = fechaEnRango(fechaInicio, fechaFin, pacienteId);
            List<Cita> listCitas = citaRepository.findAll(filtros);

            if(listCitas == null || listCitas.isEmpty()) {
                return "Ninguna cita encontrada.";
            }

            List<CitaDTO>  listCitasDTO = new ArrayList<>();

            for(Cita cita: listCitas) {
                listCitasDTO.add(CitaMapper.toDto(cita));
            }

            String list = new String();
            for(CitaDTO cita : listCitasDTO) {
                list += CitaMapper.interpretarCita(cita);
            }

            return list;

        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public String modificarCita(Long idCita, CitaUpdateDTO citaDTO, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(". "));
            return errors.toString();
        }

        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if(citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setDescripcion(citaDTO.getDescripcion());
            citaRepository.save(cita);
            return "Cita actualizada con exito! \n" + CitaMapper.interpretarCita(CitaMapper.toDto(cita));
        } else {
            return "No se encontro la cita con id: " + idCita;
        }
    }

    @Override
    public String eliminarCita(Long idCita) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if(citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setIsActive(false);
            citaRepository.save(cita);
            return "Cita eliminada con exito!";
        } else {
            return "No se encontro la cita con id: " + idCita + ".";
        }
    }
}

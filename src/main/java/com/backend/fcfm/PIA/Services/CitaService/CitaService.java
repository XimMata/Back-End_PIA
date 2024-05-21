package com.backend.fcfm.PIA.Services.CitaService;

import com.backend.fcfm.PIA.Model.Cita.CitaCreationDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaUpdateDTO;
import org.springframework.validation.BindingResult;

import java.text.ParseException;


public interface CitaService {
    String crearCita(CitaCreationDTO citaDTO, BindingResult result, Long pacienteId) throws ParseException;
    String listarCitas(String fechaInicio, String fechaFin,  Long pacienteId);
    String modificarCita(Long idCita, CitaUpdateDTO citaDTO, BindingResult result);
    String eliminarCita(Long idCita);
}

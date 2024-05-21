package com.backend.fcfm.PIA.Services.PacienteService;


import com.backend.fcfm.PIA.Entity.Paciente;
import org.springframework.validation.BindingResult;

public interface PacienteService {
    String crearPaciente(Paciente paciente, BindingResult result);
}

package com.backend.fcfm.PIA.Services.PacienteService.impl;

import com.backend.fcfm.PIA.Entity.Paciente;
import com.backend.fcfm.PIA.Repository.PacienteRepository.PacienteRepository;
import com.backend.fcfm.PIA.Services.PacienteService.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public String crearPaciente(Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(". "));
            return errors.toString();
        }

        pacienteRepository.save(paciente);
        return "Paciente creado con exito";
    }
}

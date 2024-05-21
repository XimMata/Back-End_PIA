package com.backend.fcfm.PIA.Services.DoctorService;

import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Model.Doctor.DoctorCreationDTO;
import org.springframework.validation.BindingResult;

import java.time.LocalTime;

public interface DoctorService {
    void crearDoctor(Doctor doctor);
    String validarDoctorDTO(DoctorCreationDTO doctorDTO, BindingResult result);
    String listarDoctores(String especialidad, String ciudad, LocalTime horaInicio, LocalTime horaFin);
}

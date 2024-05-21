package com.backend.fcfm.PIA.Services.DoctorService.impl;

import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Model.Doctor.DoctorCreationDTO;
import com.backend.fcfm.PIA.Model.Doctor.DoctorDTO;
import com.backend.fcfm.PIA.Repository.DoctorRepository.DoctorRepository;
import com.backend.fcfm.PIA.Services.DoctorService.DoctorService;
import com.backend.fcfm.PIA.Utils.DoctorMapper;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.backend.fcfm.PIA.Model.Doctor.DoctorEspecification.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String validarDoctorDTO(DoctorCreationDTO doctorDTO, BindingResult result) {
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(". \n"));
        String errorDays = DoctorMapper.validarDiasSemana(doctorDTO.getDias_Trabajo());
        if (errorDays != null) {
            errors.append(errorDays);
        }

        return errors.toString();
    }

    @Override
    public void crearDoctor(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRepository.save(doctor);
    }

    @Override
    public String listarDoctores(String especialidad, String ciudad, LocalTime horaInicio, LocalTime horaFin) {
        Specification<Doctor> filtros = Specification.where(StringUtils.isBlank(ciudad) ? null : ciudad(ciudad))
                                        .and(StringUtils.isBlank(especialidad) ? null : doctorEspecialidad(especialidad))
                                        .and((horaInicio == null && horaFin == null) ? null : disponibilidadEnRango(horaInicio, horaFin));

        List< Doctor > doctors = doctorRepository.findAll(filtros);

        if(doctors == null || doctors.isEmpty()) {
            return "Ningun doctor encontrado.";
        }

        List< DoctorDTO > doctorsDTO = new ArrayList<>();
        for(Doctor doctor : doctors) {
            doctorsDTO.add(DoctorMapper.toDto(doctor));
        }

        String list = new String();
        for(DoctorDTO doctor : doctorsDTO) {
            list += DoctorMapper.interpretarDoctor(doctor) + "\n";
        }

        return list;
    }

}

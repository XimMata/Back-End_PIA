package com.backend.fcfm.PIA.Controllers.Doctor.impl;

import com.backend.fcfm.PIA.Controllers.Doctor.DoctorController;
import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Model.Doctor.DoctorCreationDTO;
import com.backend.fcfm.PIA.Services.DoctorService.DoctorService;
import com.backend.fcfm.PIA.Utils.DoctorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;


@RestController
public class DoctorControllerImpl implements DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorControllerImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public ResponseEntity<String> createDoctor(@Valid @RequestBody DoctorCreationDTO doctorDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(doctorService.validarDoctorDTO(doctorDTO, result));
        }

        // OK!
        Doctor doctor = DoctorMapper.toDoctor(doctorDTO);
        doctorService.crearDoctor(doctor);

        return ResponseEntity.ok().body("Doctor creado con éxito.");
    }

    @Override
    public ResponseEntity<String> listDoctors(@RequestParam(required = false) String especialidad,
                                         @RequestParam(required = false) LocalTime horaInicial,
                                         @RequestParam(required = false) LocalTime horaFinal,
                                         @RequestParam(required = false) String ciudad)
    {
        return ResponseEntity.ok().body(doctorService.listarDoctores(especialidad, ciudad, horaInicial, horaFinal));
    }

}

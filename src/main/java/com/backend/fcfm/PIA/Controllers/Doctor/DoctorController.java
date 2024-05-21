package com.backend.fcfm.PIA.Controllers.Doctor;

import com.backend.fcfm.PIA.Model.Doctor.DoctorCreationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RequestMapping("/doctor")
public interface DoctorController {

    @PostMapping("/")
    ResponseEntity<String> createDoctor(@Valid @RequestBody DoctorCreationDTO doctorDTO, BindingResult result);

    @GetMapping("/buscar")
ResponseEntity<String > listDoctors
        (
                @RequestParam(required = false) String especialidad,
                @RequestParam(required = false) LocalTime horaInicial,
                @RequestParam(required = false) LocalTime horaFinal,
                @RequestParam(required = false) String ciudad
        );


}

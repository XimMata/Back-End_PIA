package com.backend.fcfm.PIA.Controllers.Citas.impl;

import com.backend.fcfm.PIA.Controllers.Citas.CitasController;
import com.backend.fcfm.PIA.Entity.Paciente;
import com.backend.fcfm.PIA.Model.Cita.CitaCreationDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaUpdateDTO;
import com.backend.fcfm.PIA.Services.CitaService.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class CitasControllerImpl implements CitasController {

    private final CitaService citaService;

    public CitasControllerImpl(CitaService citaService) {
        this.citaService = citaService;
    }

    @Override
    public ResponseEntity<String> crearCita(@Valid @RequestBody CitaCreationDTO citaDTO, BindingResult result) throws ParseException {
        return ResponseEntity.ok().body(citaService.crearCita(citaDTO, result, getAuthId()));
    }

    @Override
    public ResponseEntity<String> listarCitas(@RequestParam(required = false) String fechaInicio,
                                              @RequestParam(required = false) String fechaFin
                                             )
    {
        return ResponseEntity.ok().body(citaService.listarCitas(fechaInicio, fechaFin, getAuthId()));
    }

    @Override
    public ResponseEntity<String> modificarCita(@PathVariable Long idCita, @Valid @RequestBody CitaUpdateDTO citaDTO, BindingResult result) {
        return ResponseEntity.ok().body(citaService.modificarCita(idCita, citaDTO, result));
    }

    @Override
    public ResponseEntity<String> eliminarCita(@PathVariable Long idCita) {
        return ResponseEntity.ok().body(citaService.eliminarCita(idCita));
    }

    public Long getAuthId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Paciente paciente = (Paciente) auth.getPrincipal();
        return paciente.getPacienteId();
    }
}

package com.backend.fcfm.PIA.Controllers.Especialidad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping (value ="/especialidad")
public interface EspecialidadController {

    @GetMapping("/")
    ResponseEntity<?> listarEspecialidades();
}

package com.backend.fcfm.PIA.Controllers.Especialidad.impl;

import com.backend.fcfm.PIA.Controllers.Especialidad.EspecialidadController;
import com.backend.fcfm.PIA.Model.Especialidad.EspecialidadDTO;
import com.backend.fcfm.PIA.Services.EspecialidadService.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EspecialidadControllerImpl implements EspecialidadController {

    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadControllerImpl(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @Override
    public ResponseEntity<?> listarEspecialidades() {
        List<EspecialidadDTO> list = especialidadService.listarEspecialidades();
        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.ok().body("Sin especialidades.");
    }
}

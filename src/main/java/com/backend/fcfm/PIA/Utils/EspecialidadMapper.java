package com.backend.fcfm.PIA.Utils;

import com.backend.fcfm.PIA.Entity.Especialidad;
import com.backend.fcfm.PIA.Model.Especialidad.EspecialidadDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class EspecialidadMapper {

    public static EspecialidadDTO toDto(Especialidad especialidad) {
        EspecialidadDTO especialidadDTO = EspecialidadDTO.builder()
                .nombre(especialidad.getEspecialidad())
                .build();

        return especialidadDTO;
    }

    public static List<Especialidad> eliminarDuplicados(List<Especialidad> especialidades) {
        Set<String> nombresUnicos = new HashSet<>();

        return especialidades.stream()
                .filter(especialidad -> nombresUnicos.add(especialidad.getEspecialidad()))
                .collect(Collectors.toList());
    }
}

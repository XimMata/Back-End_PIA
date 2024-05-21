package com.backend.fcfm.PIA.Model.Doctor;

import com.backend.fcfm.PIA.Entity.DiasTrabajo;
import com.backend.fcfm.PIA.Entity.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String nombre;
    private String apellido_Paterno;
    private String apellido_Materno;
    private String ciudad;
    private String correo;
    private LocalTime hora_Inicia;
    private LocalTime hora_Termina;
    private List<Especialidad> especialidades_Medicas;
    private List<DiasTrabajo> dias_Trabajo;
}

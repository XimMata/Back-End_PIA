package com.backend.fcfm.PIA.Model.Cita;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CitaDTO {
    private Long idCita; // Id de la cita.
    private String nombreDoctor; // Doctor de la cita
    private String nombrePaciente; // Paciente de la cita.
    private Date fechaCita; // Fecha y hora de la cita.
    private String ciudad; // Ciudad donde se hara la cita.
    private String descripcion; // Descripcion de la cita.
    private Boolean isActive; // Cita aun vigente?

}

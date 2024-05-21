package com.backend.fcfm.PIA.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctor_Id;

    private String nombre;

    private String apellido_Paterno;

    private String apellido_Materno;

    private String correo;

    private String password;

    private String ciudad; // Ciudad donde se ubica la consultoria del Doctor.

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime hora_Inicia; // Hora en la que el medico empieza a recibir citas.

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime hora_Termina; // Hora en la que el medico dejara de recibir citas.

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Especialidad> especialidades_Medicas; // Especialidades del Doctor.

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DiasTrabajo> dias_Trabajo; // Dias en los que trabaja un Doctor.
}
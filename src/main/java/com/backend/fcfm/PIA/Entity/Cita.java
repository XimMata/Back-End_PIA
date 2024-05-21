package com.backend.fcfm.PIA.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_Id", nullable = false)
    private Long cita_Id; //Id de la cita en MySQL.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_Id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_Id")
    private Paciente paciente;

    private Date fechaCita;
    private String ciudad;
    private String descripcion;
    private Boolean isActive;
}

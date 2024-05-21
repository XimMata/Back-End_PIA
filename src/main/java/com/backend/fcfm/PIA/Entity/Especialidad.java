package com.backend.fcfm.PIA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long especialidad_Id;

    private String especialidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_Id")
    @JsonIgnore
    private Doctor doctor;

    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}

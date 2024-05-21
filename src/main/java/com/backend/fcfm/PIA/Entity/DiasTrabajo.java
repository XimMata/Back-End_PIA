package com.backend.fcfm.PIA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="DiasTrabajo")
public class DiasTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diasTrabajo_Id;

    private String diaTrabajo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_Id")
    @JsonIgnore
    private Doctor doctor;

    public DiasTrabajo(String diaTrabajo) {
        this.diaTrabajo = diaTrabajo;
    }
}

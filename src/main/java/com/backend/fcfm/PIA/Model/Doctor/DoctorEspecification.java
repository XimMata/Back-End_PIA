package com.backend.fcfm.PIA.Model.Doctor;

import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Entity.Especialidad;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

public class DoctorEspecification {

    public static Specification<Doctor> ciudad(String ciudad) {
        return (root, query, builder) -> builder.like(root.get("ciudad"), "%" + ciudad + "%");
    }


    public static Specification<Doctor> doctorEspecialidad(String especialidad) {
        return (root, query, builder) -> {
            Join<Doctor, Especialidad> especialidadesJoin = root.join("especialidades_Medicas");

            return builder.equal(especialidadesJoin.get("especialidad"), especialidad);
        };
    }

    public static Specification<Doctor> disponibilidadEnRango(LocalTime horaInicio, LocalTime horaFin) {
        return (root, query, builder) -> {
            Predicate startsBeforeEnd = builder.lessThanOrEqualTo(root.get("hora_Inicia"), horaFin);
            Predicate endsAfterStart = builder.greaterThanOrEqualTo(root.get("hora_Termina"), horaInicio);

            return builder.and(startsBeforeEnd, endsAfterStart);
        };
    }

}

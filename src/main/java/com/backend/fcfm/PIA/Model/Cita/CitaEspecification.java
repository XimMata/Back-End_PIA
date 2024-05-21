package com.backend.fcfm.PIA.Model.Cita;

import com.backend.fcfm.PIA.Entity.Cita;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;


public class CitaEspecification {

    public static Specification<Cita> fechaEnRango(Date fechaInicio, Date fechaFin, Long pacienteId) {
        return (root, query, criteriaBuilder) -> {
            Predicate fecha = criteriaBuilder.between(root.get("fechaCita"), fechaInicio, fechaFin);
            Predicate paciente = criteriaBuilder.equal(root.get("paciente").get("pacienteId"), pacienteId);

            if(fechaInicio == null || fechaFin == null) {
                return criteriaBuilder.and(paciente);
            }

            return criteriaBuilder.and(fecha, paciente);
        };
    }

    public static Specification<Cita> citaEnFecha(Date fechaCita, Long doctorId) {
        return (root, query, criteriaBuilder) -> {
            Predicate fechaPredicate = criteriaBuilder.equal(root.get("fechaCita"), fechaCita);
            Predicate doctorPredicate = criteriaBuilder.equal(root.get("doctor").get("doctor_Id"), doctorId);
            return criteriaBuilder.and(fechaPredicate, doctorPredicate);
        };
    }
}

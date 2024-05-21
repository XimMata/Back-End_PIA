package com.backend.fcfm.PIA.Utils;


import com.backend.fcfm.PIA.Entity.DiasTrabajo;
import com.backend.fcfm.PIA.Entity.Doctor;
import com.backend.fcfm.PIA.Entity.Especialidad;
import com.backend.fcfm.PIA.Model.Doctor.DoctorCreationDTO;
import com.backend.fcfm.PIA.Model.Doctor.DoctorDTO;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Component
public class DoctorMapper {
    public static DoctorDTO toDto(Doctor doctor) {
        DoctorDTO doctorDTO = DoctorDTO.builder()
                            .id(doctor.getDoctor_Id())
                            .nombre(doctor.getNombre())
                            .apellido_Paterno(doctor.getApellido_Paterno())
                            .apellido_Materno(doctor.getApellido_Materno())
                            .ciudad(doctor.getCiudad())
                            .correo(doctor.getCorreo())
                            .hora_Inicia(doctor.getHora_Inicia())
                            .hora_Termina(doctor.getHora_Termina())
                            .especialidades_Medicas(doctor.getEspecialidades_Medicas())
                            .dias_Trabajo(doctor.getDias_Trabajo())
                            .build();

        return doctorDTO;
    }

    public static Doctor toDoctor(DoctorCreationDTO doctorDTO) {
        Doctor doctor = Doctor.builder()
                .nombre(doctorDTO.getNombre())
                .apellido_Paterno(doctorDTO.getApellido_Paterno())
                .apellido_Materno(doctorDTO.getApellido_Materno())
                .ciudad(doctorDTO.getCiudad())
                .correo(doctorDTO.getCorreo())
                .hora_Inicia(doctorDTO.getHora_Inicia())
                .hora_Termina(doctorDTO.getHora_Termina())
                .password(doctorDTO.getPassword())
                .build();

        List<Especialidad> especialidades = new ArrayList<>();
        for (String especialidad : doctorDTO.getEspecialidades_Medicas()) {
            Especialidad esp = new Especialidad(especialidad);
            esp.setDoctor(doctor);
            especialidades.add(esp);
        }
        doctor.setEspecialidades_Medicas(especialidades);

        List<DiasTrabajo> diasTrabajo = new ArrayList<>();
        for (String diaTrabajo : doctorDTO.getDias_Trabajo()) {
            DiasTrabajo dia = new DiasTrabajo(diaTrabajo);
            dia.setDoctor(doctor);
            diasTrabajo.add(dia);
        }
        doctor.setDias_Trabajo(diasTrabajo);

        return doctor;
    }

    public static String interpretarDoctor(DoctorDTO doctor) {

        String doctorData = "#Id: " + doctor.getId() + "\n" +"Dr. " + doctor.getNombre() + " " + doctor.getApellido_Paterno() + " " + doctor.getApellido_Materno() + "\n" +
                "Consultorio en: " + doctor.getCiudad() + "\n" +
                "Correo: " + doctor.getCorreo() + "\n" +
                "Hora de Citas: " + doctor.getHora_Inicia() + " a "+ doctor.getHora_Termina() + "\n" +
                "Especialidades Médicas: " + "\n";
        for (Especialidad especialidad : doctor.getEspecialidades_Medicas()) {
            doctorData += "-" + especialidad.getEspecialidad() + "\n";
        }
        doctorData += "Días de Trabajo: " + "\n";
        for (DiasTrabajo dia : doctor.getDias_Trabajo()) {
            doctorData += "-" + dia.getDiaTrabajo() + "\n";
        }

        return doctorData;
    }

    private static final Pattern DIA_SEMANA_PATTERN = Pattern.compile(
            "lunes|martes|miercoles|jueves|viernes|sabado|domingo",
            Pattern.CASE_INSENSITIVE
    );

    public static String validarDiasSemana(List<String> listaDias) {
        for (String dia : listaDias) {
            if (!isValidDay(dia)) {
                return "Ingrese los dias que admite citas (Lunes, Viernes, etc).";
            }
        }
        return null;
    }

    public static boolean isValidDay(String input) {
        // Normaliza el texto eliminando los acentos
        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Verifica si el texto normalizado coincide con el patrón
        return DIA_SEMANA_PATTERN.matcher(normalizedInput).matches();
    }

}

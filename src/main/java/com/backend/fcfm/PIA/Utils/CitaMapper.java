package com.backend.fcfm.PIA.Utils;

import com.backend.fcfm.PIA.Entity.Cita;
import com.backend.fcfm.PIA.Model.Cita.CitaCreationDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;


@Component
public class CitaMapper {

    public static CitaDTO toDto(Cita cita) {
        CitaDTO citaDTO = CitaDTO.builder()
                .nombreDoctor(cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellido_Paterno() + " " + cita.getDoctor().getApellido_Materno())
                .nombrePaciente(cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellidoPaterno() + " " + cita.getPaciente().getApellidoMaterno())
                .fechaCita(cita.getFechaCita())
                .descripcion(cita.getDescripcion())
                .ciudad(cita.getCiudad())
                .isActive(cita.getIsActive())
                .idCita(cita.getCita_Id())
                .build();

        return citaDTO;
    }

    public static Cita toCita(CitaCreationDTO citaDTO) throws ParseException {
        Cita cita = Cita.builder()
                .fechaCita(DateMapper.parseDate(citaDTO.getFechaCita()))
                .ciudad(citaDTO.getCiudad())
                .descripcion(citaDTO.getDescripcion())
                .build();

        return cita;
    }

    public static String interpretarCita(CitaDTO citaDTO) {

        String citaData = "Dr. " + citaDTO.getNombreDoctor() + "\n" +
                "Paciente: " + citaDTO.getNombrePaciente() + "\n" +
                "Consultorio en: " + citaDTO.getCiudad() + "\n" +
                "Hora de Cita: " + citaDTO.getFechaCita() + "\n" +
                "Descripcion: " + citaDTO.getDescripcion() + "\n" +
                "Estado de Cita: " + (citaDTO.getIsActive() ? "Activa" : "Cancelada") + "\n" +
                "Id de Cita: " + citaDTO.getIdCita() + "\n\n";

        return citaData;
    }
}

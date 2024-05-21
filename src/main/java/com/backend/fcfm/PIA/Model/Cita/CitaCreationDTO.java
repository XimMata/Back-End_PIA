package com.backend.fcfm.PIA.Model.Cita;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CitaCreationDTO {
    @NotNull(message = "Inserte el Id del Doctor con el cual agendar cita.")
    private Long doctorId; // Doctor de la cita

    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}", message = "Inserte la fecha con formato dd-MM-YYYY HH:mm")
    @NotNull(message = "Inserte la fecha para agendar cita.")
    private String fechaCita; // Fecha y hora de la cita.

    @NotNull(message = "Inserte la ciudad donde sera su cita.")
    private String ciudad; // Ciudad donde se hara la cita.

    @NotNull(message = "Inserte una descripcion referente a su cita.")
    private String descripcion; // Descripcion de la cita.

}

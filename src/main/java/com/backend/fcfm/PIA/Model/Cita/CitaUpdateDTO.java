package com.backend.fcfm.PIA.Model.Cita;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaUpdateDTO {
    @NotNull(message = "Ingrese una descripcion valida.")
    private String descripcion;
}

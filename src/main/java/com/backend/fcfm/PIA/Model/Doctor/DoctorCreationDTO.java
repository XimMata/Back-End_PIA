package com.backend.fcfm.PIA.Model.Doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DoctorCreationDTO {

    @NotNull(message = "Ingrese su nombre.")
    private String nombre;

    @NotNull(message = "Ingrese su apellido paterno.")
    private String apellido_Paterno;

    @NotNull(message = "Ingrese su apellido materno.")
    private String apellido_Materno;

    @NotNull(message = "Ingrese donde se ubica su consultoria.")
    private String ciudad;

    @Email(message = "Ingrese un correo valido.")
    @NotNull(message = "Ingrese su correo.")
    private String correo;

    @NotNull(message = "Ingrese la hora en que recibe citas.")
    private LocalTime hora_Inicia;

    @NotNull(message = "Ingrese la hora en que dejara de recibir citas.")
    private LocalTime hora_Termina;

    @NotEmpty(message = "Ingrese sus especialidad(es).")
    private List<String> especialidades_Medicas;

    @NotEmpty(message = "Ingrese los dias que admite citas (Lunes, Viernes, etc).")
    private List<String> dias_Trabajo;

    @Size(min = 10, message = "Ingrese una contraseña de mínimo 10 caracteres.")
    @NotNull(message = "Ingrese su contraseña.")
    private String password;
}

package com.backend.fcfm.PIA.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Porfavor, ingrese su nombre(s).")
    private String nombre;
    @NotNull(message = "Porfavor, ingrese su apellido paterno.")
    private String apellidoPaterno;
    @NotNull(message = "Porfavor, ingrese su apellido materno.")
    private String apellidoMaterno;
    @Email(message = "Porfavor, ingrese un correo valido.")
    private String email;
    @NotNull(message = "Porfavor, ingrese su contraseña.")
    @Size(min = 10, message = "Ingrese una contraseña que incluya mas de 10 caracteres.")
    private String password;
}

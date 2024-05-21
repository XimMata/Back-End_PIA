package com.backend.fcfm.PIA.Auth;

import com.backend.fcfm.PIA.Config.JwtService;
import com.backend.fcfm.PIA.Entity.Paciente;
import com.backend.fcfm.PIA.Entity.Role;
import com.backend.fcfm.PIA.Repository.PacienteRepository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PacienteRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public StringBuilder errorMessage(BindingResult result) {
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(". \n"));
        return errors;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var paciente = Paciente.builder()
                .nombre(request.getNombre())
                .apellidoPaterno(request.getApellidoPaterno())
                .apellidoMaterno(request.getApellidoMaterno())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(paciente);
        var jwtToken = jwtService.generateToken(paciente);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var paciente = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(paciente);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

package com.backend.fcfm.PIA.Controllers.Citas;

import com.backend.fcfm.PIA.Model.Cita.CitaCreationDTO;
import com.backend.fcfm.PIA.Model.Cita.CitaUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequestMapping("/cita")
public interface CitasController {

    @PostMapping("/")
    ResponseEntity<String> crearCita(@Valid @RequestBody CitaCreationDTO citaDTO, BindingResult result) throws ParseException;

    @GetMapping("/buscar")
    ResponseEntity<String> listarCitas(
            @RequestParam(required = false) String fechaInicial,
            @RequestParam(required = false) String fechaTermina
            );

    @PutMapping("/modificar/{idCita}")
    ResponseEntity<String> modificarCita(@PathVariable Long idCita, @Valid @RequestBody CitaUpdateDTO citaDTO, BindingResult result);

    @DeleteMapping("/eliminar/{idCita}")
    ResponseEntity<String> eliminarCita(@PathVariable Long idCita);
}

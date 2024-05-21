package com.backend.fcfm.PIA.Repository.CitaRepository;

import com.backend.fcfm.PIA.Entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CitaRepository extends JpaRepository<Cita, Long>, JpaSpecificationExecutor<Cita> {
}

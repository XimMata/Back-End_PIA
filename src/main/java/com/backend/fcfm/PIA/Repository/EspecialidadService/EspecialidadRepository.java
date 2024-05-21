package com.backend.fcfm.PIA.Repository.EspecialidadService;

import com.backend.fcfm.PIA.Entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long>, JpaSpecificationExecutor<Especialidad> {

}

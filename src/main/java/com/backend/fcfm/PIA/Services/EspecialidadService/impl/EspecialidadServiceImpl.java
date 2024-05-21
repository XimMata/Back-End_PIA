package com.backend.fcfm.PIA.Services.EspecialidadService.impl;

import com.backend.fcfm.PIA.Entity.Especialidad;
import com.backend.fcfm.PIA.Model.Especialidad.EspecialidadDTO;
import com.backend.fcfm.PIA.Repository.EspecialidadService.EspecialidadRepository;
import com.backend.fcfm.PIA.Services.EspecialidadService.EspecialidadService;
import com.backend.fcfm.PIA.Utils.EspecialidadMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<EspecialidadDTO> listarEspecialidades() {

        List<Especialidad> listEntity= especialidadRepository.findAll();

        if(listEntity.size() == 0) {
            return null;
        }

        listEntity = EspecialidadMapper.eliminarDuplicados(listEntity);

        List<EspecialidadDTO> listDTO = new ArrayList<EspecialidadDTO>();

        for(Especialidad e : listEntity) {
            listDTO.add(EspecialidadMapper.toDto(e));
        }

        return listDTO;
    }
}

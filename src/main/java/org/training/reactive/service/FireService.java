package org.training.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.mappers.FireMapper;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Siren;
import org.training.reactive.repository.FireRepository;
import org.training.reactive.repository.SirenRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class FireService {

    @Autowired
    FireRepository fireRepository;
    @Autowired
    SirenRepository sirenRepository;
    @Autowired
    FireMapper mapper;


    public FireResponseDTO addFire(FireRequestDTO fireRequestDTO){

        Fire fire = new Fire(fireRequestDTO.latitude(), fireRequestDTO.longtitude(), fireRequestDTO.status());

        // triggerSirens(fire);

        return mapper.toDTO(fireRepository.save(fire));
    }

    public void triggerSirens(Fire fire) {

    }

}

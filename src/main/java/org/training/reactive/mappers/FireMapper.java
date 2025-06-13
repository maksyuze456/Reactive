package org.training.reactive.mappers;

import org.springframework.stereotype.Component;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.dto.SirenResponseDTO;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Siren;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireMapper {


    public Fire toEntity(FireRequestDTO fireRequestDTO) {
        return new Fire(fireRequestDTO.latitude(), fireRequestDTO.longtitude(), fireRequestDTO.status(), Collections.emptyList());
    }

    public FireResponseDTO toDTO(Fire fire) {
        return new FireResponseDTO(fire.getId(), fire.getLatitude(), fire.getLongtitude(), fire.getStatus(),
                fire.getTriggeredSirens().stream()
                        .map(siren -> new SirenResponseDTO(siren.getId(), siren.getLatitude(), siren.getLongtitude(), siren.getStatus()))
                        .collect(Collectors.toList()));
    }

    public void updateEntity(Fire fire, FireResponseDTO fireResponseDTO) {
        fire.setId(fireResponseDTO.id());
        fire.setLatitude(fireResponseDTO.latitude());
        fire.setLongtitude(fireResponseDTO.longtitude());
        fire.setStatus(fireResponseDTO.status());
        fire.setTriggeredSirens(fireResponseDTO.triggeredSirens().stream()
                .map(siren -> new Siren(siren.id(), siren.latitude(), siren.longtitude(), siren.status(), List.of(fire)))
                .collect(Collectors.toList()));
    }

}

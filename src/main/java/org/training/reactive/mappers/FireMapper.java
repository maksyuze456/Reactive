package org.training.reactive.mappers;

import org.springframework.stereotype.Component;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.model.Fire;

@Component
public class FireMapper {


    public Fire toEntity(FireRequestDTO fireRequestDTO) {
        return new Fire(fireRequestDTO.latitude(), fireRequestDTO.longtitude(), fireRequestDTO.status());
    }

    public FireResponseDTO toDTO(Fire fire) {
        return new FireResponseDTO(fire.getId(), fire.getLatitude(), fire.getLongtitude(), fire.getStatus(), fire.getTriggeredSirens());
    }

    public void updateEntity(Fire fire, FireResponseDTO fireResponseDTO) {
        fire.setId(fireResponseDTO.id());
        fire.setLatitude(fireResponseDTO.latitude());
        fire.setLongtitude(fireResponseDTO.longtitude());
        fire.setStatus(fireResponseDTO.status());
        fire.setTriggeredSirens(fireResponseDTO.triggeredSirens());
    }

}

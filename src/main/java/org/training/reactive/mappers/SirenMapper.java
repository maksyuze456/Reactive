package org.training.reactive.mappers;

import org.springframework.stereotype.Component;
import org.training.reactive.dto.SirenRequestDTO;
import org.training.reactive.dto.SirenResponseDTO;
import org.training.reactive.model.Siren;

import java.util.Collections;

@Component
public class SirenMapper {

    public SirenMapper() {

    }

    public Siren toEntity(SirenRequestDTO sirenDTO) {
        return new Siren(sirenDTO.latitude(), sirenDTO.longtitude(), sirenDTO.status(), Collections.emptyList());
    }

    public SirenResponseDTO toDto(Siren siren) {
        return new SirenResponseDTO(siren.getId(), siren.getLatitude(), siren.getLongtitude(), siren.getStatus());
    }

    public void updateEntity(Siren siren, SirenResponseDTO sirenResponseDTO) {
        siren.setId(sirenResponseDTO.id());
        siren.setLatitude(sirenResponseDTO.latitude());
        siren.setLongtitude(sirenResponseDTO.longtitude());
        siren.setActiveFires(Collections.emptyList());
    }


}

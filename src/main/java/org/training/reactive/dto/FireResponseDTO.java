package org.training.reactive.dto;

import org.training.reactive.model.Siren;
import org.training.reactive.model.Status;

import java.util.List;

public record FireResponseDTO(long id, double latitude, double longtitude, Status status, List<SirenResponseDTO> triggeredSirens) {
}

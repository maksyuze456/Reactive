package org.training.reactive.dto;

import org.training.reactive.model.Status;

public record SirenResponseDTO(long id, double latitude, double longtitude, Status status) {
}

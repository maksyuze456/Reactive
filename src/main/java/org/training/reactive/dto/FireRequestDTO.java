package org.training.reactive.dto;

import org.training.reactive.model.Status;

public record FireRequestDTO(double latitude, double longtitude, Status status) {
}

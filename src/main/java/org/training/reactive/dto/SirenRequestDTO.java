package org.training.reactive.dto;

import org.training.reactive.model.Status;

public record SirenRequestDTO(double latitude, double longtitude, Status status) {
}

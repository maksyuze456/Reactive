package org.training.reactive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.training.reactive.model.Status;

@JsonIgnoreProperties(ignoreUnknown = false)
public record FireRequestDTO(double latitude, double longtitude, Status status) {
}

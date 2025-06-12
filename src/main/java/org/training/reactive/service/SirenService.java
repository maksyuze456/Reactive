package org.training.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.training.reactive.dto.SirenRequestDTO;
import org.training.reactive.dto.SirenResponseDTO;
import org.training.reactive.model.Siren;
import org.training.reactive.repository.SirenRepository;
import org.training.reactive.mappers.SirenMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SirenService {

    @Autowired
    SirenRepository sirenRepository;
    @Autowired
    SirenMapper mapper;

    public SirenResponseDTO addSiren(SirenRequestDTO sirenRequestDTO) {
        Siren sirenObj = mapper.toEntity(sirenRequestDTO);
        return mapper.toDto(sirenRepository.save(sirenObj));

    }

    public List<SirenResponseDTO> findAll() {
        return sirenRepository.findAll().stream()
                .map(siren -> mapper.toDto(siren))
                .collect(Collectors.toList());
    }

    public SirenResponseDTO updateSiren(SirenResponseDTO sirenResponseDTO) {
        Siren sirenToUpdate = sirenRepository.findById(sirenResponseDTO.id())
                .orElseThrow(() -> new RuntimeException("Siren with id " + sirenResponseDTO.id() + " not found"));
        mapper.updateEntity(sirenToUpdate, sirenResponseDTO);
        return mapper.toDto(sirenRepository.save(sirenToUpdate));
    }


    public void deleteSiren(long id) {
        Siren sirenToDelete = sirenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Siren with id " + id + " not found"));
        sirenRepository.delete(sirenToDelete);
    }


}

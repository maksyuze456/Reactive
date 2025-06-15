package org.training.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.mappers.FireMapper;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Location;
import org.training.reactive.model.Siren;
import org.training.reactive.model.Status;
import org.training.reactive.repository.FireRepository;
import org.training.reactive.repository.SirenRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FireService {
    private static final int EARTH_RADIUS_KM = 6371;

    @Autowired
    FireRepository fireRepository;
    @Autowired
    SirenRepository sirenRepository;
    @Autowired
    FireMapper mapper;


    public FireResponseDTO addFire(FireRequestDTO fireRequestDTO){
        Fire fire = mapper.toEntity(fireRequestDTO);

        List<Siren> triggeredSirens = triggerSirens(fire);

        if (!triggeredSirens.isEmpty()) {
            fire.setTriggeredSirens(triggeredSirens);
        } else {
            fire.setTriggeredSirens(Collections.emptyList());
        }
        Fire savedFire = fireRepository.save(fire);
        return mapper.toDTO(savedFire);
    }

    public List<FireResponseDTO> getAllActiveFires(Status status) {
        List<Fire> fires = fireRepository.findAllByStatus(status);

        List<FireResponseDTO> firesDTO = fires.stream()
                .map(fire -> mapper.toDTO(fire))
                .collect(Collectors.toList());
        return firesDTO;
    }

    public List<Siren> triggerSirens(Fire fire) {
        Location fireLocation = new Location(fire.getLatitude(), fire.getLongtitude());

        List<Siren> triggeredSirens = sirenRepository.findAll().stream()
                .filter(siren -> {
                    Location sirenLocation = new Location(siren.getLatitude(), siren.getLongtitude());
                    double distanceKm = calculateDistanceKM(fireLocation, sirenLocation);
                    if (distanceKm <=10.0 && distanceKm >= 0.0){
                        siren.setStatus(Status.ACTIVE);
                        return true;
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        return triggeredSirens;
    }

    public double calculateDistanceKM(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongtitude());

        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongtitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public FireResponseDTO disableFire(long id) {
        Fire fireToDisable = fireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fire with id " + id + " not found"));
        fireToDisable.setStatus(Status.DISABLED);
        fireToDisable.getTriggeredSirens().forEach(siren -> siren.setStatus(Status.DISABLED));
        return mapper.toDTO(fireRepository.save(fireToDisable));
    }
}

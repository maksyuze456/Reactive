package org.training.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.mappers.FireMapper;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Location;
import org.training.reactive.model.Siren;
import org.training.reactive.repository.FireRepository;
import org.training.reactive.repository.SirenRepository;

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

        Fire fire = new Fire(fireRequestDTO.latitude(), fireRequestDTO.longtitude(), fireRequestDTO.status());

        // triggerSirens(fire);

        return mapper.toDTO(fireRepository.save(fire));
    }

    public void triggerSirens(Fire fire) {
        Location fireLocation = new Location(fire.getLatitude(), fire.getLongtitude());

        List<Siren> triggeredSirens = sirenRepository.findAll().stream()
                .filter(siren -> {
                    Location sirenLocation = new Location(siren.getLatitude(), siren.getLongtitude());
                    double distanceKm = calculateDistanceKM(fireLocation, sirenLocation);
                    if (distanceKm <=10 && distanceKm >= 0){
                        return true;
                    } else {
                        return false;
                    }
                })
                .toList();


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

}

package org.training.reactive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.training.reactive.model.Siren;
import org.training.reactive.model.Status;
import org.training.reactive.repository.SirenRepository;

import java.util.Collections;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    SirenRepository sirenRepository;
    @Override
    public void run(String... args) throws Exception {


        Siren s1 = new Siren(34.08, -118.54, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s1);

        Siren s2 = new Siren(34.07, -118.57, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s2);

        Siren s3 = new Siren(34.04, -118.55, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s3);

        Siren s4 = new Siren(34.07, -118.49, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s4);

        Siren s5 = new Siren(34.05, -118.50, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s5);

        Siren s6 = new Siren(34.04, -118.52, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s6);

        Siren s7 = new Siren(34.03, -118.483, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s7);

        Siren s8 = new Siren(33.98, -118.46, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s8);

        Siren s9 = new Siren(34.00, -118.48, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s9);

        Siren s10 = new Siren(34.02, -118.44, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s10);

    }

}

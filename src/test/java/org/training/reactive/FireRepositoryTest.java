package org.training.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Siren;
import org.training.reactive.model.Status;
import org.training.reactive.repository.FireRepository;
import org.training.reactive.repository.SirenRepository;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DataJpaTest

public class FireRepositoryTest {

    @Autowired
    FireRepository fireRepository;
    @Autowired
    SirenRepository sirenRepository;

    @BeforeEach
    public void initData() {
        Siren s1 = new Siren(50.55, 145.25, Status.DISABLED, Collections.emptyList());
        Siren s2 = new Siren(55.55, -36.25, Status.DISABLED, Collections.emptyList());
        sirenRepository.save(s1);
        sirenRepository.save(s2);
        List<Siren> sirens = List.of(s1, s2);

        Fire f1 = new Fire(50.55, 145.25, Status.ACTIVE, sirens);
        Fire f2 = new Fire(55.55, -36.25, Status.DISABLED, Collections.emptyList());

        fireRepository.save(f1);
        fireRepository.save(f2);

    }

    @Test
    public void testIfFiresRetrievedCorrect() {

        List<Fire> fires = fireRepository.findAll();

        assertEquals(2, fires.size());
        assertInstanceOf(Fire.class, fires.get(0));
        assertThat(fires, containsInAnyOrder(
                hasProperty("latitude", is(50.55)),
                hasProperty("latitude", is(55.55))
        ));


    }



}

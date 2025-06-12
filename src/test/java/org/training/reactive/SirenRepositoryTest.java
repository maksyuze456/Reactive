package org.training.reactive;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.training.reactive.model.Siren;
import org.training.reactive.model.Status;
import org.training.reactive.repository.SirenRepository;
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.util.Collections;
import java.util.List;

@DataJpaTest
public class SirenRepositoryTest {

    @Autowired
    SirenRepository sirenRepository;

    @BeforeEach
    public void initData() {

        Siren s1 = new Siren(50.55, 145.25, Status.DISABLED, Collections.emptyList());
        Siren s2 = new Siren(55.55, -36.25, Status.DISABLED, Collections.emptyList());

        sirenRepository.save(s1);
        sirenRepository.save(s2);

    }

    @Test
    public void testIfSirensRetrievedCorrect() {

        List<Siren> sirens = sirenRepository.findAll();

        assertEquals(2, sirens.size());
        assertInstanceOf(Siren.class, sirens.get(0));
        assertThat(sirens, containsInAnyOrder(
                hasProperty("latitude", is(50.55)),
                hasProperty("latitude", is(55.55))
        ));


    }

}

package org.training.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.reactive.model.Location;
import org.training.reactive.service.FireService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FireServiceTest {

    FireService fireService;

    public FireServiceTest() {
        fireService = new FireService();
    }

    @Test
    public void testIfDistanceCalculationCorrect() {

        Location loc1 = new Location(34.04, -118.54);
        Location loc2 = new Location(34.04, -118.53);
        Location loc3 = new Location(34.07, -118.37);

        double distance = fireService.calculateDistanceKM(loc1, loc2);

        double above10km = fireService.calculateDistanceKM(loc2, loc3);

        assertTrue(distance <= 2.0);
        assertFalse(above10km < 10);

    }

}

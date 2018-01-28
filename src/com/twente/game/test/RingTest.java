package com.twente.game.test;

import com.twente.game.helper.Ring;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RingTest {

    Ring ring = new Ring();


    @Test
    public void Rings() {

        ring.subtract(0);
        ring.subtract(0);
        ring.subtract(0);
        int base = ring.getBaseTile();
        assertEquals(base, 0);


        ring.subtract(1);
        ring.subtract(1);
        ring.subtract(1);
        int j = ring.getSmall();
        assertEquals(j, 0);

        ring.subtract(2);
        ring.subtract(2);
        int b = ring.getMedium();
        assertEquals(b, 1);

        ring.subtract(3);
        int h = ring.getBig();
        assertEquals(h,2);

        ring.subtract(4);
        int g = ring.getHuge();
        assertEquals(g, 2);


    }
}

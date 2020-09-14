package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class SwellTest {
    private Swell testSwell;

    @BeforeEach
    void runBefore() {
        testSwell = new Swell();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testSwell.getDirection());
        assertEquals(0,testSwell.getPeriod());
        assertEquals(0,testSwell.getOneThirdHeight());
        assertEquals(0,testSwell.getOneTenthHeight());
    }

    @Test
    void testUpdateSwell() {
        testSwell.updateSwell(30,10,1,2);

        assertEquals(30, testSwell.getDirection());
        assertEquals(10,testSwell.getPeriod());
        assertEquals(1,testSwell.getOneThirdHeight());
        assertEquals(2,testSwell.getOneTenthHeight());
    }
}

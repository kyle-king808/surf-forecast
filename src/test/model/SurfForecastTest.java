package model;

import exceptions.HeightWrongOrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SurfForecastTest {
    private SurfForecast forecastOne;
    private Beach beach1;

    @BeforeEach
    void runBefore() {
        forecastOne = new SurfForecast();
    }

    @Test
    void testConstructor(){
        assertFalse(null==forecastOne);
    }

    @Test
    void testUpdateSwellNoBeaches() {
        try {
            forecastOne.updateSwell(20,10,1,3);
        } catch (HeightWrongOrderException e) {
            fail();
        }

        assertEquals(20,forecastOne.getSwellDirection());
        assertEquals(10,forecastOne.getSwellPeriod());
        assertEquals(1,forecastOne.getOneThirdSwellHeight());
        assertEquals(3,forecastOne.getOneTenthSwellHeight());
    }

    @Test
    void testAddBeach() {
        beach1 = new Beach("Beach1",270);
        forecastOne.addBeach(beach1);

        assertEquals(beach1,forecastOne.getBeachI(0));
    }

    @Test
    void testAddSwellWithBeach() {
        beach1 = new Beach("Beach1",270);
        forecastOne.addBeach(beach1);
        try {
            forecastOne.updateSwell(200,10,1,3);
        } catch (HeightWrongOrderException e) {
            fail();
        }

        assertEquals(0,beach1.getOneThirdHeight());
        assertEquals(1,beach1.getOneTenthHeight());
    }

    @Test
    void testAddSwellWithBeachTwo() {
        beach1 = new Beach("Beach1",200);
        forecastOne.addBeach(beach1);
        try {
            forecastOne.updateSwell(270,10,1,3);
        } catch (HeightWrongOrderException e) {
            fail();
        }
        assertEquals(0,beach1.getOneThirdHeight());
        assertEquals(1,beach1.getOneTenthHeight());
    }

    @Test
    void testGetBeachList() {
        beach1 = new Beach("Beach1",270);
        forecastOne.addBeach(beach1);

        assertEquals(beach1,forecastOne.getBeachList().get(0));
    }

    @Test
    void testHeightWrongOrderExceptionThrown() {
        try {
            forecastOne.updateSwell(50,10,5,2);
            fail();
        } catch (HeightWrongOrderException e) {}
    }

    @Test
    void testHeightWrongOrderExceptionNotThrown() {
        try {
            forecastOne.updateSwell(50,10,2,5);

        } catch(HeightWrongOrderException e) {
            fail();
        }
    }
}

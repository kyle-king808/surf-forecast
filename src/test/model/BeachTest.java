package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeachTest {
    private Beach testBeach;
    private Beach testBeachBoundary;
    private Beach testBeachBoundaryTwo;

    @BeforeEach
    void runBefore() {
        testBeach = new Beach("Makapuu",80);
        testBeachBoundary = new Beach("NorthBeach",0);

    }

    @Test
    void testConstructor(){
        assertEquals("Makapuu",testBeach.getName());
        assertEquals(80,testBeach.getDirection());
        assertEquals("Makapuu",testBeach.toString());
    }

    @Test
    void testAddReview() {
        testBeach.addReview("Beautiful beach on the east side of Oahu. -Local Resident");

        assertEquals("Beautiful beach on the east side of Oahu. -Local Resident",
                testBeach.getReviews().get(0));
    }

    @Test
    void testAddMultipleReviews() {
        testBeach.addReview("Beautiful beach on the east side of Oahu. -Local Resident");
        testBeach.addReview("Scary waves! but beautiful sunrise. -First Time Tourist");

        assertEquals("Beautiful beach on the east side of Oahu. -Local Resident",
                testBeach.getReviews().get(0));
        assertEquals("Scary waves! but beautiful sunrise. -First Time Tourist",
                testBeach.getReviews().get(1));
    }

    @Test
    void testAddSwellOnce() {
        testBeach.addSwell(90,2,4);

        assertEquals(2,testBeach.getOneThirdHeight());
        assertEquals(4,testBeach.getOneTenthHeight());
    }

    @Test
    void testAddSwellMultipleTimes() {
        testBeach.addSwell(90,2,4);

        assertEquals(2,testBeach.getOneThirdHeight());
        assertEquals(4,testBeach.getOneTenthHeight());

        testBeach.addSwell(200, 4,10);

        assertEquals(0,testBeach.getOneThirdHeight());
        assertEquals(0,testBeach.getOneTenthHeight());
    }

    @Test
    void testAddSwellBeachDirectionZero() {
        testBeachBoundary.addSwell(0,2,4);

        assertEquals(2,testBeachBoundary.getOneThirdHeight());
        assertEquals(4,testBeachBoundary.getOneTenthHeight());

    }

    @Test
    void testAddSwellBoundaryCondition() {
        testBeachBoundary.addSwell(314,2,4);

        assertEquals(1,testBeachBoundary.getOneThirdHeight());
        assertEquals(2,testBeachBoundary.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionTwo() {
        testBeachBoundary.addSwell(269,2,4);

        assertEquals(0,testBeachBoundary.getOneThirdHeight());
        assertEquals(0,testBeachBoundary.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionThree() {
        testBeachBoundary.addSwell(359,2,4);

        assertEquals(2,testBeachBoundary.getOneThirdHeight());
        assertEquals(4,testBeachBoundary.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionFour() {
        testBeachBoundary.addSwell(46,2,4);

        assertEquals(1,testBeachBoundary.getOneThirdHeight());
        assertEquals(2,testBeachBoundary.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionFive() {
        testBeachBoundary.addSwell(91,2,4);

        assertEquals(0,testBeachBoundary.getOneThirdHeight());
        assertEquals(0,testBeachBoundary.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionSix() {
        testBeachBoundaryTwo = new Beach("SouthBeach",200);
        testBeachBoundaryTwo.addSwell(5,2,4);

        assertEquals(0,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(0,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionSeven() {
        testBeachBoundaryTwo = new Beach("SouthBeach",160);
        testBeachBoundaryTwo.addSwell(350,2,4);

        assertEquals(0,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(0,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionEight() {
        testBeachBoundaryTwo = new Beach("SouthBeach",200);
        testBeachBoundaryTwo.addSwell(110,2,4);

        assertEquals(1,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(2,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionNine() {
        testBeachBoundaryTwo = new Beach("SouthBeach",160);
        testBeachBoundaryTwo.addSwell(250,2,4);

        assertEquals(1,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(2,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionTen() {
        testBeachBoundaryTwo = new Beach("SouthBeach",180);
        testBeachBoundaryTwo.addSwell(90,2,4);

        assertEquals(1,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(2,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionEleven() {
        testBeachBoundaryTwo = new Beach("SouthBeach",180);
        testBeachBoundaryTwo.addSwell(89,2,4);

        assertEquals(0,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(0,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionTwelve() {
        testBeachBoundaryTwo = new Beach("SouthBeach",180);
        testBeachBoundaryTwo.addSwell(270,2,4);

        assertEquals(1,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(2,testBeachBoundaryTwo.getOneTenthHeight());
    }

    @Test
    void testAddSwellBoundaryConditionThirteen() {
        testBeachBoundaryTwo = new Beach("SouthBeach",180);
        testBeachBoundaryTwo.addSwell(271,2,4);

        assertEquals(0,testBeachBoundaryTwo.getOneThirdHeight());
        assertEquals(0,testBeachBoundaryTwo.getOneTenthHeight());
    }

}

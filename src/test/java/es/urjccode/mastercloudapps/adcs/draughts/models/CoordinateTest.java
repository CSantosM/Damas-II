package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).getCoordinatesBetween(new Coordinate(0, 0)).get(0));
        assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).getCoordinatesBetween(new Coordinate(4, 0)).get(0));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).getCoordinatesBetween(new Coordinate(4, 4)).get(0));
        assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).getCoordinatesBetween(new Coordinate(0, 4)).get(0));
    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
    }

}
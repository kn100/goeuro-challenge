package busapi;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Tests for Route.java
 * Created by Kevin Norman on 03/12/16.
 */
public class RouteTest {
    static Route routeTest;


    @BeforeClass
    public static void setUp() throws Exception
    {
        Map<Integer, Integer> inRouteStops = new TreeMap<Integer,Integer>();
        //Key = stop ID, Value = ordering;
        inRouteStops.put(1,1);
        inRouteStops.put(2,0);
        inRouteStops.put(3,3);
        inRouteStops.put(4,2);

        routeTest = new Route();
        routeTest.setRouteID(5);
        routeTest.setRouteStops(inRouteStops);

    }
    @Test
    public void routeIDCheck() {
        assertEquals(5,routeTest.getRouteID());
    }
    @Test
    public void possibleRoute() {
        assertEquals(true, routeTest.routePossible(1,4));
    }
    @Test
    public void oneStopPossibleRoute() {
        assertEquals(true, routeTest.routePossible(2,3));
    }
    @Test
    public void reverseNotPossible() {
        assertEquals(false, routeTest.routePossible(4,1));
    }
    @Test
    public void bothStopsNotInList() {
        assertEquals(false, routeTest.routePossible(5,8));
    }
}
package busapi;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Tests for RouteRepository.java
 * Created by Kevin Norman on 03/12/16.
 */

public class RouteRepositoryTest {

    /** completely valid input to test, possible route */
    @Test
    public void parseValidFilePossibleRoute() throws Exception {
        StringReader input = new StringReader("3\n1 1 1 2 3 4\n2 4 3 2 1\n3 9 7 8 6");
        BufferedReader inputbr = new BufferedReader(input);
        RouteRepository testRR = RouteRepositoryFactory.makeRouteRepository(inputbr);

        String resp = testRR.routePossible("2","3");
        String correctresp = "{\"dep_sid\":2,\"arr_sid\":3,\"direct_bus_route\":true}";
        assertEquals(correctresp,resp);
    }

    /** completely valid input to test, impossible route */
    @Test
    public void parseValidFileImpossibleRoute() throws Exception {
        StringReader input = new StringReader("3\n1 1 1 2 3 4\n2 4 3 2 1\n3 9 7 8 6");
        BufferedReader inputbr = new BufferedReader(input);
        RouteRepository testRR = RouteRepositoryFactory.makeRouteRepository(inputbr);

        String resp = testRR.routePossible("3","90");
        String correctresp = "{\"dep_sid\":3,\"arr_sid\":90,\"direct_bus_route\":false}";
        assertEquals(correctresp,resp);
    }

    /** Valid rows, but wrong row count. Should still run, albiet with slightly reduced perf */
    @Test
    public void parseWrongRC() throws Exception {

        StringReader input = new StringReader("2\n1 1 1 2 3 4\n2 4 3 2 1\n3 9 7 8 6");
        BufferedReader inputbr = new BufferedReader(input);
        RouteRepository testRR = RouteRepositoryFactory.makeRouteRepository(inputbr);

        String resp = testRR.routePossible("4","2");
        String correctresp = "{\"dep_sid\":4,\"arr_sid\":2,\"direct_bus_route\":true}";
        assertEquals(correctresp,resp);
    }

    /** Malformed input, throws exception */
    @Test(expected=IllegalArgumentException.class)
    public void malformedInput() throws Exception {
        StringReader input = new StringReader("3\n1 1 1 2 3 4\n2 4 3 2INVALID 1\n3 9 7 8 6");
        BufferedReader inputbr = new BufferedReader(input);
        RouteRepository testRR = RouteRepositoryFactory.makeRouteRepository(inputbr);
    }

    /** No text, throws exception */
    @Test(expected=NumberFormatException.class)
    public void noInput() throws Exception {
        StringReader input = new StringReader("");
        BufferedReader inputbr = new BufferedReader(input);
        RouteRepository testRR = RouteRepositoryFactory.makeRouteRepository(inputbr);
    }
}

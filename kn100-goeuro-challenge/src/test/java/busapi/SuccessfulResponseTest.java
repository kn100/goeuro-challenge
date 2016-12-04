package busapi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kn100 on 04/12/16.
 */
public class SuccessfulResponseTest {
    @Test
    public void testPossibleSuccessfulResponse() {
        Response testError = new SuccessfulResponse(5,6,true);
        String e = "{\"dep_sid\":5,\"arr_sid\":6,\"direct_bus_route\":true}";
        assertEquals(e,testError.jsonify());
    }
    @Test
    public void testImpossibleSuccessfulResponse() {
        Response testError = new SuccessfulResponse(6,5,false);
        String e = "{\"dep_sid\":6,\"arr_sid\":5,\"direct_bus_route\":false}";
        assertEquals(e,testError.jsonify());
    }
}
package busapi;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Error Response format test
 * Created by Kevin Norman on 04/12/16.
 */
public class ErrorResponseTest {
    ResponseFactory rf = new ResponseFactory();
    @Test
    public void testErrorJSON() {

        Response testError = rf.makeErrorResponse("TestError");
        assertEquals("{\"error\":\"TestError\"}",testError.jsonify());
    }
    @Test
    public void blankErrorTextTest() {
        Response testError = rf.makeErrorResponse("");
        assertEquals("{\"error\":\"\"}",testError.jsonify());
    }
}
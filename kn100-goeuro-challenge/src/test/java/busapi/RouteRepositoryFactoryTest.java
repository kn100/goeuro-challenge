package busapi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Route repository factory tests
 * Created by Kevin Norman on 04/12/16.
 */
public class RouteRepositoryFactoryTest {


    @Test
    public void RouteRepositoryConstructionTest() {
        RouteRepositoryFactory rf = new RouteRepositoryFactory();
        assertNotEquals(null,rf);
    }
}
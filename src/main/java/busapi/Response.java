package busapi;

/**
 * Response.java
 * Defines the form of the data to be returned to the client on a successful request.
 *
 * Created by Kevin Norman on 02/12/16.
 */

class Response {
    /**
     * The departing stations ID
     */
    int dep_sid;
    /**
     * The arriving stations ID
     */
    int arr_sid;
    /**
     * True if the route is possible, flase if not
     */
    boolean direct_bus_route;
}

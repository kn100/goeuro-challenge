package busapi;

/**
 * Response.java
 * Defines the form of the data to be returned to the client on a successful request.
 *
 * Created by Kevin Norman on 02/12/16.
 */

class SuccessfulResponse implements Response {
    /**
     * The departing stations ID
     */
    private int dep_sid;
    /**
     * The arriving stations ID
     */
    private int arr_sid;
    /**
     * True if the route is possible, false if not
     */
    private boolean direct_bus_route;


    public SuccessfulResponse(int dep_sid, int arr_sid, boolean direct_bus_route) {
        this.dep_sid = dep_sid;
        this.arr_sid = arr_sid;
        this.direct_bus_route = direct_bus_route;
    }
}

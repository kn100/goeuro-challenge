package busapi;

import java.util.List;

/**
 * RouteRepository.java
 * Stores a list of route objects, and parses through them all in log(n) time complexity.
 * Created by Kevin Norman on 02/12/16.
 */
class RouteRepository {

    /**
     * List of route objects
     */
    private List<Route> routes;

    public RouteRepository(List<Route> inRoutes) {
        routes = inRoutes;
    }

    /**
     * Checks to see if the specified route is possible
     *
     * @param str_dep_sid the departing stop
     * @param str_arr_sid the arriving stop
     * @return a JSON representation of the response object containing this data, as well as whether
     */
    String routePossible(String str_dep_sid, String str_arr_sid) {
        ResponseFactory rf = new ResponseFactory();
        int dep_sid, arr_sid;
        dep_sid = Integer.parseInt(str_dep_sid);
        arr_sid = Integer.parseInt(str_arr_sid);


        int routeNumber = checkRoutePossible(dep_sid, arr_sid);
        Boolean direct_bus_route = routeNumber != -1;

        Response response = rf.makeSuccessfulResponse(dep_sid, arr_sid, direct_bus_route);
        return response.jsonify();

    }

    /**
     * Iterates through all stored route objects and checks if the route is possible.
     *
     * @param dep_sid the departing stop
     * @param arr_sid the arriving stop
     * @return the route number where this route is possible, or -1 if not possible.
     */
    private int checkRoutePossible(int dep_sid, int arr_sid) {
        for (Route routeBeingChecked : routes) {
            if (routeBeingChecked.routePossible(dep_sid, arr_sid)) {
                return routeBeingChecked.getRouteID();
            }
        }
        return -1;
    }
}

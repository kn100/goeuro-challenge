package busapi;

import java.util.Map;

/**
 * Route.java
 * Class that stores a route ID along with a key/value pair consisting of the stops on the route and their ordering.
 * Created by Kevin Norman on 02/12/16.
 */
public class Route {
    /** The route ID */
    private int routeID;
    /** Map of stops for this route where key = sid, value = original position before sorting. */
    private Map<Integer, Integer> routeStops;

    /** @return route ID */
    int getRouteID() {
        return this.routeID;
    }

    /** @param inRoute route ID */
    void setRouteID(int inRoute) {
        this.routeID = inRoute;
    }

    /** @param inRouteStops Map where key = sid, value = original position before sorting. */
    void setRouteStops(Map<Integer, Integer> inRouteStops) {
        this.routeStops = inRouteStops;
    }

    /**
     * Checks if the given route is possible.
     * @param src source stop SID
     * @param des destination stop SID
     * @return true if possible, false if not.
     */
    boolean routePossible(int src, int des) {
        if(this.routeStops.containsKey(src) && this.routeStops.containsKey(des)) {
            return (this.routeStops.get(src) < routeStops.get(des));
        }
        return false;
    }
}

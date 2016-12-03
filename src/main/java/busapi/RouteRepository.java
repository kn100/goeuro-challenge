package busapi;

/**
 * RouteRepository.java
 * Stores a list of route objects, and parses through them all in log(n) time complexity.
 * Created by kn100 on 02/12/16.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class RouteRepository {

    /**
     * List of route objects
     */
    private List<Route> routes;


    /**
     * Constructor for the routeRepository
     * @param path The path
     * @throws IOException if the supplied path is nonexistant or the file is formatted wrongly
     */
    RouteRepository(String path) throws IOException {
        System.out.println("Attempting to parse route file");
        routes = readRouteFile(path);
    }

    /**
     * A function that accepts a file path, and initialises routes for each line of the file
     * @param path A string containing the path to the file to be parsed
     * @return a List of route objects filled with data from the file
     * @throws IOException if the file is nonexistant or invalid
     */
    private static List<Route> readRouteFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        // The first line defines how many routes there are
        String firstLine = br.readLine();
        int numberOfRoutes;

        try {
            numberOfRoutes = Integer.parseInt(firstLine);
        } catch (NumberFormatException e) {
            // the file doesn't even start correctly
            throw new IOException("File failed to correctly state the number of rows it contains (Not a number)");
        }

        List<Route> listOfMaps = new ArrayList<Route>(numberOfRoutes);
        for (String line; (line = br.readLine()) != null; ) {
            listOfMaps.add(readLineOfRouteFile(line));
        }
        br.close();
        return listOfMaps;
    }

    /**
     * Parses a line into a route object
     * @param line A string containing a space separated string of integers
     * @return a route object containing the data specified in the line
     * @throws IllegalArgumentException if the line is not correctly formatted.
     */
    private static Route readLineOfRouteFile(String line) throws IllegalArgumentException {
        String pattern = "^(([0-9]+\\s{1})+([0-9]+))$";
        Pattern p = Pattern.compile(pattern);
        Matcher m;

        //Verify the line is valid, according to the Regex above
        m = p.matcher(line);
        if (!m.find()) {
            throw new IllegalArgumentException("A line was invalid. Line: " + line);
        }

        String[] strSplitListOfCities = line.split(" ");
        Map<Integer, Integer> intSplitListOfCities = new TreeMap<Integer, Integer>();
        Route newRoute = new Route();

        // iterate over each city
        int key;
        for (int i = 0; i < strSplitListOfCities.length; i++) {
            try {
                key = Integer.parseInt(strSplitListOfCities[i]);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("A row was not correctly formatted. Row contents: " +
                        Arrays.toString(strSplitListOfCities));
            }

            if (i == 0) {
                //first element of every valid line is always the route ID.
                newRoute.setRouteID(key);
            } else {
                //other elements are stops.
                intSplitListOfCities.put(key, i);
            }
        }
        newRoute.setRouteStops(intSplitListOfCities);
        return newRoute;
    }

    /**
     * Checks to see if the specified route is possible
     * @param str_dep_sid the departing stop
     * @param str_arr_sid the arriving stop
     * @return a JSON representation of the response object containing this data, as well as whether
     */
    String routePossible(String str_dep_sid, String str_arr_sid) {
        Response response = new Response();
        int dep_sid, arr_sid;
        try {
            dep_sid = Integer.parseInt(str_dep_sid);
            arr_sid = Integer.parseInt(str_arr_sid);
        } catch (NumberFormatException e) {
            return "Invalid request";
        }

        int routeNumber = checkRoutePossible(dep_sid, arr_sid);
        response.dep_sid = dep_sid;
        response.arr_sid = arr_sid;
        if (routeNumber > -1) {
            response.direct_bus_route = true;
        } else {
            response.direct_bus_route = false;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(response));
        return gson.toJson(response);
    }

    /**
     * Iterates through all stored route objects and checks if the route is possible.
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

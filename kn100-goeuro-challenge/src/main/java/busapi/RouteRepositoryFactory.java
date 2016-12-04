package busapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Constructs a RouteRepository object given a BufferedReader.
 * Created by Kevin Norman on 04/12/16.
 */
class RouteRepositoryFactory {
    static RouteRepository makeRouteRepository(BufferedReader br) throws IOException {
        return new RouteRepository(readRouteFile(br));
    }

    /**
     * A function that BufferedReader, and initialises routes for each line
     *
     * @param br A BufferedReader containing data to parse
     * @return a List of route objects filled with data from the file
     * @throws IOException if the file is nonexistant or invalid
     */
    private static List<Route> readRouteFile(BufferedReader br) throws IOException, NumberFormatException {
        // The first line defines how many routes there are
        String firstLine = br.readLine();
        int numberOfRoutes = Integer.parseInt(firstLine);


        List<Route> listOfMaps = new ArrayList<Route>(numberOfRoutes);
        for (String line; (line = br.readLine()) != null; ) {
            listOfMaps.add(readLineOfRouteFile(line));
        }
        br.close();
        return listOfMaps;
    }

    /**
     * Parses a line into a route object
     *
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
        for (int i = 0; i < strSplitListOfCities.length; i++) {
            if (i == 0) {
                //first element of every valid line is always the route ID.
                newRoute.setRouteID(Integer.parseInt(strSplitListOfCities[i]));
            } else {
                //other elements are stops.
                intSplitListOfCities.put(Integer.parseInt(strSplitListOfCities[i]), i);
            }
        }
        newRoute.setRouteStops(intSplitListOfCities);
        return newRoute;
    }
}

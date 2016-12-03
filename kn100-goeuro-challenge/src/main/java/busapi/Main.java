package busapi;

/**
 * Main.java
 * Entry point to the system, defines the main method.
 * Created by Kevin Norman on 02/12/16.
 */

import java.io.File;
import java.io.IOException;

import static spark.Spark.*;

public class Main {
    /**
     * Entry point to the route checker.
     * @param args = First argument should be a path to the file that should parsed.
     */
    public static void main(String[] args) {
	setPort(8088);
        RouteRepository allRoutes;
        File f;
        try {
            f = new File(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a file as the only argument.");
            return;
        }
        if(!f.isFile()) {
            System.out.println("The file specified could not be found.");
            return;
        }

        try {
            allRoutes = new RouteRepository(f.getAbsolutePath());
            before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

            get("/api/direct", (req, res) ->
                    allRoutes.routePossible(req.queryParams("dep_sid"),
                                            req.queryParams("arr_sid")));
        } catch (IllegalArgumentException|IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting.");
        }
    }
}

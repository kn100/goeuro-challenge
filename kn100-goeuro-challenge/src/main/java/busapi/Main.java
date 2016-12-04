package busapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;


/**
 * Main.java
 * Entry point to the system, defines the main method.
 * Created by Kevin Norman on 02/12/16.
 */
public class Main {
    /**
     * The port that the API will listen on
     */
    private static final int listenPort = 8088;

    /**
     * Entry point to the route checker.
     *
     * @param args = First argument should be a path to the file that should parsed.
     */
    public static void main(String[] args) {
        File f;
        port(listenPort); //set server port, 8088 by default
        if (args.length == 0) {
            System.out.println("Please specify a file as the only argument.");
            return;
        }

        f = new File(args[0]);

        if (!f.isFile()) {
            System.out.println("The file specified could not be found.");
            return;
        }

        RouteRepository routeFile;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
            routeFile = RouteRepositoryFactory.makeRouteRepository(br);

        } catch (Exception e) {
            System.out.println("An error occurred while parsing the file. Error: " + e.getMessage());
            System.out.println("Exiting.");
            return;
        }


        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
        get("/api/direct", (req, res) ->
                routeFile.routePossible(req.queryParams("dep_sid"),
                        req.queryParams("arr_sid")));
    }
}

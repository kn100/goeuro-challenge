# Bus Route Challenge for GoEuro
A microservice which:
* Parses a text file where:
  * The first line describes how many lines there are not including itself,
  * All preceding lines consist of at least 3 space separated integers, where the first integer is considered the unique ID, and that the rest of the numbers are considered 'stops' along the route. Stops are unique within a route.
* Delivers a microservice on port 8088 where a get request consisting of ```http://localhost:8088/api/direct?dep_sid={x}&arr_sid={y}``` will recieve a reply ```{ "dep_sid": x, "arr_sid": y, "direct_bus_route": true/false }``` where if the route is possible, direct_bus_route will be true, false otherwise. A route is possible if both numbers exist on one line, and x appears before y.

## Libraries
* [Spark Java](http://sparkjava.com/) web framework for the server
* [Google GSON](https://github.com/google/gson) to convert Java Objects into JSON
* Builds with Gradle, I've also included a task called fatJar if you desire a Jar that bundles all the dependencies together.

## Performance
* Internally uses a Treemap collection for storage of route data, which is held in memory. This guarantees Θ(log(n)) performance in the average case, and Θ(n) in the worst case.
* On a dataset containing 100,000 routes, each with 1000 stops, where the stop IDs are random numbers between 0 and 1,000,000, the API is able to respond to any query almost instantly.

## Usage
To build the project, you can run ```gradle fatJar``` within the repository, and a built Jar containing all required dependencies will appear within ```build/lib```, which can then be started by running ```java -jar build/lib/goeurobusapi-all-1.0-SNAPSHOT.jar tests/docker/example``` where example is a dataset that follows the conventions above. You can, of course, supply your own.

I have included example scripts in the form of service.sh, which you can use ```./service.sh start|stop|block /file/to/parse``` to control the microservice, as well as build.sh which simply does ```gradle fatJar```. Test files supplied by GoEuro are also provided in tests/

## Contact me
[@normankev141](https://twitter.com/normankev141) - Feel free to tweet at me!
[kn100.me](https://kn100.me)



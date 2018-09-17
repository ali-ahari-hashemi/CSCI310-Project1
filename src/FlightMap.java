import java.util.*;

/**
 * FlightMap Class
 *
 *
 *
 */
public class FlightMap {

    /**
     * FlightMap Constructor
     *
     * creates a FlightMap class with the given origin city
     *
     * @param originCity
     *
     */
    public FlightMap(char originCity) {
        this.originCity = originCity;
        this.cities = new HashSet<Character>();
        cities.add(originCity);
        this.flights = new HashMap<Character, List<Flight>>();
        this.flights.put(originCity, new ArrayList<Flight>());
    }

    /**
     * addFlight
     *
     * Adds a flight between the two given cities and its corresponding cost
     *
     * @param char departure
     * @param char destination
     * @param int cost
     * @return void
     */
    public void addFlight(char departure, char destination, int cost) {
        cities.add(departure);
        cities.add(destination);

        Flight flight = new Flight(departure, destination, cost);
        List<Flight> flights = this.flights.get(departure);

        if (flights != null) {
           flights.add(flight);
        }
        else {
            flights = new ArrayList<Flight>();
            flights.add(flight);
            this.flights.put(departure, flights);
        }
    }


    /**
     * getFlights
     *
     * Gets corresponding flight information for a given departure city
     *
     * @param char departure
     *
     * @return String: "Destination1 Cost1 Destination2 Cost2 etc...
     */
    public String getFlights(char departure) {
        List<Flight> flights = this.flights.get(departure);
        String result = "";
        if (flights!=null) {
            for (int i = 0; i < flights.size(); i++) {
                result += flights.get(i).destination + " " + flights.get(i).cost;
            }
        }
        return result;

    }


    /**
     * getRouteInfo
     *
     * Will compute routes for all destinations and the total cost of each route
     *
     * @return Formatted string of all route info
     */
    public String getRouteInfo() {
        String allRouteInfo = "Destination\tFlight Route from " + Character.toString(this.originCity) + "\tTotal Cost\n";
        Iterator<Character> it = this.cities.iterator();
        while(it.hasNext()) {
            char destination = it.next();
            if (destination != this.originCity) {
                String formattedRouteInfo = getFlightRouteFormattedString(destination);
                if (formattedRouteInfo != null) {
                    allRouteInfo += formattedRouteInfo + "\n";
                }
            }
        }
        return allRouteInfo;
    }


    private String getFlightRouteFormattedString(char destination) {
        List<Flight> flightRoute = getFlightRouteToDestination(destination);
        if (flightRoute == null) { return null; }

        String formattedOutput = Character.toString(destination) + "\t";
        String route = Character.toString(this.originCity);
        int totalCost = 0;

        for (int i = 0; i < flightRoute.size(); i++) {
            Flight currentFlight = flightRoute.get(i);
            route += ", " + currentFlight.destination;
            totalCost += currentFlight.cost;
        }
        formattedOutput += route + "\t" + "$" + Integer.toString(totalCost);

        return formattedOutput;
    }


    private List<Flight> getFlightRouteToDestination(char destination) {
       List<Flight> flightRoute = helper(this.originCity, destination, new ArrayList<Flight>(), new HashSet<Character>());
       return flightRoute;
    }


    private List<Flight> helper(char currentCity, char destination, List<Flight> flightsVisited, HashSet<Character> citiesVisited) {

        if (currentCity == destination) {
            return flightsVisited;
        }

        List<Flight> flightsFromCurrentCity = this.flights.get(currentCity);
        if (flightsFromCurrentCity != null) {
            for (int i = 0; i < flightsFromCurrentCity.size(); i++) {
                if (!citiesVisited.contains(currentCity)) {
                    Flight currentFlight = flightsFromCurrentCity.get(i);
                    flightsVisited.add(currentFlight);
                    citiesVisited.add(currentCity);
                    List<Flight> flightRoute = helper(currentFlight.destination, destination, flightsVisited, citiesVisited);
                    if (flightRoute != null) {
                        return flightRoute;
                    }
                    flightsVisited.remove(currentFlight);
                    citiesVisited.remove(currentCity);
                }
            }
        }

        return null;
    }

    private char originCity;
    private Set<Character> cities;
    private Map<Character, List<Flight>> flights;

    private class Flight {
        public Flight(char departure, char destination, int cost) {
            this.departure = departure;
            this.destination = destination;
            this.cost = cost;
        }
        public char departure;
        public char destination;
        public int cost;
    }

}

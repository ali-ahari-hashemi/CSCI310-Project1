public class SearchMap {

    public static void main(String[] args) {

        FlightMap flightMap = new FlightMap('P');
        flightMap.addFlight('P', 'W', 200);
        flightMap.addFlight('P', 'R', 300);
        flightMap.addFlight('R', 'X', 200);
        flightMap.addFlight('Q', 'X', 375);
        flightMap.addFlight('W', 'S', 250);
        flightMap.addFlight('S', 'T', 300);
        flightMap.addFlight('T', 'W', 350);
        flightMap.addFlight('W', 'Y', 500);
        flightMap.addFlight('Y', 'Z', 450);
        flightMap.addFlight('Y', 'R', 600);

        String result = flightMap.getRouteInfo();
        System.out.println(result);

    }

}

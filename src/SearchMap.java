import java.io.BufferedReader;
import java.io.FileReader;

public class SearchMap {

    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            char origin = line.charAt(0);
            FlightMap flightMap = new FlightMap(origin);

            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                char departure = lineSplit[0].charAt(0);
                char destination = lineSplit[1].charAt(0);
                int cost = Integer.parseInt(lineSplit[2]);
                flightMap.addFlight(departure, destination, cost);
            }

            String result = flightMap.getRouteInfo();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

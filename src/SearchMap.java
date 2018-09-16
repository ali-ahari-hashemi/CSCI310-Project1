import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class SearchMap {

    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String line = br.readLine();
            char origin = line.charAt(0);
            FlightMap flightMap = new FlightMap(origin);

            // Build flight map from input file data
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                char departure = lineSplit[0].charAt(0);
                char destination = lineSplit[1].charAt(0);
                int cost = Integer.parseInt(lineSplit[2]);
                flightMap.addFlight(departure, destination, cost);
            }
            br.close();

            // Get route info from flight map and save it to the output file
            String result = flightMap.getRouteInfo();
            PrintWriter out = new PrintWriter(outputFilePath);
            out.println(result);
            out.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

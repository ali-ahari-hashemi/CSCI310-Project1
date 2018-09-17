import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlightMap {


    @Test
    public void testAddFlight() {
        FlightMap tester = new FlightMap('A');
        tester.addFlight('A', 'B', 100);
        assertTrue(tester.getFlights('A') == "B 100");
    }

    @Test
    public void testGetRouteInfo() {
        FlightMap tester = new FlightMap('A');
        tester.addFlight('A', 'B', 100);
        tester.addFlight('B', 'C', 200);
        assertTrue(tester.getRouteInfo() == "Destination\tFlight Route From A\tTotal Cost\nB\tA, B\t$100\nC\tA, B, C\t$300");
    }


}

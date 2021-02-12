import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final int HOUR = 2;

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        LocalDateTime localDateTimeOne = LocalDateTime.now();
        LocalDateTime localDateTimeTwo = localDateTimeOne.plusHours(HOUR);
        List<Flight> flightList = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(s -> convertToLocalDateTime(s.getDate()).getHour() >= localDateTimeOne.getHour() &
                        convertToLocalDateTime(s.getDate()).getHour() <= localDateTimeTwo.getHour() &
                        s.getType().equals(Flight.Type.DEPARTURE))
                .collect(Collectors.toList());
        flightList.forEach(System.out::println);
        return flightList;
    }

    private static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }
}
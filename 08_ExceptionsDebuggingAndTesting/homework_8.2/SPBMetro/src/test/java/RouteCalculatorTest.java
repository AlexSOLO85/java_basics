import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> routeOnSingleLine;
    List<Station> routeWithOneTransfer;
    List<Station> routeWithTwoTransfers;

    RouteCalculator routeCalculator;
    StationIndex stationIndex;
    Line lineFirst, lineSecond, lineThird;
    Station st_1_line1, st_2_line1, st_3_line1,
            st_1_line2, st_2_line2, st_3_line2,
            st_1_line3, st_2_line3, st_3_line3;

    @Override
    protected void setUp() {
        routeOnSingleLine = new ArrayList<>();
        routeWithOneTransfer = new  ArrayList<>();
        routeWithTwoTransfers = new  ArrayList<>();

        stationIndex = new StationIndex();

        lineFirst = new Line(1, "First");
        lineSecond = new Line(2, "Second");
        lineThird = new Line(3, "Third");

        stationIndex.addLine(lineFirst);
        stationIndex.addLine(lineSecond);
        stationIndex.addLine(lineThird);

        st_1_line1 = new Station("L1-ST-1", lineFirst);
        st_2_line1 = new Station("L1-ST-2", lineFirst);
        st_3_line1 = new Station("L1-ST-3", lineFirst);

        lineFirst.addStation(st_1_line1);
        lineFirst.addStation(st_2_line1);
        lineFirst.addStation(st_3_line1);

        st_1_line2 = new Station("L2-ST-1", lineSecond);
        st_2_line2 = new Station("L2-ST-2", lineSecond);
        st_3_line2 = new Station("L2-ST-3", lineSecond);

        lineSecond.addStation(st_1_line2);
        lineSecond.addStation(st_2_line2);
        lineSecond.addStation(st_3_line2);

        st_1_line3 = new Station("L3-ST-1", lineThird);
        st_2_line3 = new Station("L3-ST-2", lineThird);
        st_3_line3 = new Station("L3-ST-3", lineThird);

        lineThird.addStation(st_1_line3);
        lineThird.addStation(st_2_line3);
        lineThird.addStation(st_3_line3);

        stationIndex.addStation(st_1_line1);
        stationIndex.addStation(st_2_line1);
        stationIndex.addStation(st_3_line1);
        stationIndex.addStation(st_1_line2);
        stationIndex.addStation(st_2_line2);
        stationIndex.addStation(st_3_line2);
        stationIndex.addStation(st_1_line3);
        stationIndex.addStation(st_2_line3);
        stationIndex.addStation(st_3_line3);

        List<Station> connection1to2 = new ArrayList<>();
        connection1to2.add(st_3_line1);
        connection1to2.add(st_1_line2);
        stationIndex.addConnection(connection1to2);

        List<Station> connection2to3 = new ArrayList<>();
        connection2to3.add(st_3_line2);
        connection2to3.add(st_1_line3);
        stationIndex.addConnection(connection2to3);

        routeCalculator = new RouteCalculator(stationIndex);

        routeOnSingleLine.add(st_1_line1);
        routeOnSingleLine.add(st_2_line1);
        routeOnSingleLine.add(st_3_line1);

        routeWithOneTransfer.add(st_1_line1);
        routeWithOneTransfer.add(st_2_line1);
        routeWithOneTransfer.add(st_3_line1);
        routeWithOneTransfer.add(st_1_line2);
        routeWithOneTransfer.add(st_2_line2);
        routeWithOneTransfer.add(st_3_line2);

        routeWithTwoTransfers.add(st_1_line1);
        routeWithTwoTransfers.add(st_2_line1);
        routeWithTwoTransfers.add(st_3_line1);
        routeWithTwoTransfers.add(st_1_line2);
        routeWithTwoTransfers.add(st_2_line2);
        routeWithTwoTransfers.add(st_3_line2);
        routeWithTwoTransfers.add(st_1_line3);
        routeWithTwoTransfers.add(st_2_line3);
        routeWithTwoTransfers.add(st_3_line3);
    }

    public void test_calculate_duration() {
        calculateDurationSingleLine();
        calculateDurationTwoLine();
        calculateDurationThreeLine();
    }

    public void test_get_shortest_route() {
        distanceToSameStation();
        stationsNextToEachOtherOnSingleLine();
        oppositeStationsOnSingleLine();
        oppositeStationsWithOneTransfer();
        oppositeStationsWithTwoTransfers();
    }

    private void calculateDurationSingleLine() {
        double actual = RouteCalculator.calculateDuration(routeOnSingleLine);
        double expected = 5;
        assertEquals(expected, actual);
    }

    private void calculateDurationTwoLine() {
        double actual = RouteCalculator.calculateDuration(routeWithOneTransfer);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    private void calculateDurationThreeLine() {
        double actual = RouteCalculator.calculateDuration(routeWithTwoTransfers);
        double expected = 22;
        assertEquals(expected, actual);
    }

    private void distanceToSameStation() {
        List<Station> expected = new ArrayList<>(){{
            add(st_1_line1);
        }};
        List<Station> actual = new ArrayList<>
                (routeCalculator.getShortestRoute(stationIndex.getStation("L1-ST-1"),
                        stationIndex.getStation("L1-ST-1")));
        assertEquals(expected, actual);
    }

    private void stationsNextToEachOtherOnSingleLine() {
        List<Station> expected = new ArrayList<>(){{
            add(st_1_line1);
            add(st_2_line1);
        }};
        List<Station> actual = new ArrayList<>
                (routeCalculator.getShortestRoute(stationIndex.getStation("L1-ST-1"),
                        stationIndex.getStation("L1-ST-2")));
        assertEquals(expected, actual);
    }

    private void oppositeStationsOnSingleLine() {
        List<Station> expected = new ArrayList<>(routeOnSingleLine);
        List<Station> actual = new ArrayList<>
                (routeCalculator.getShortestRoute(stationIndex.getStation("L1-ST-1"),
                        stationIndex.getStation("L1-ST-3")));
        assertEquals(expected, actual);
    }

    private void oppositeStationsWithOneTransfer() {
        List<Station> expected = new ArrayList<>(routeWithOneTransfer);
        List<Station> actual = new ArrayList<>
                (routeCalculator.getShortestRoute(stationIndex.getStation("L1-ST-1"),
                        stationIndex.getStation("L2-ST-3")));
        assertEquals(expected, actual);
    }

    private void oppositeStationsWithTwoTransfers() {
        List<Station> expected = new ArrayList<>(routeWithTwoTransfers);
        List<Station> actual = new ArrayList<>
                (routeCalculator.getShortestRoute(stationIndex.getStation("L1-ST-1"),
                        stationIndex.getStation("L3-ST-3")));
        assertEquals(expected, actual);
    }
}
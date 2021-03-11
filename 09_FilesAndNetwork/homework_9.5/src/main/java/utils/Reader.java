package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Reader {
    private static final String GET_STATIONS = "stations";

    private Reader() {
    }

    public static void readFile(String path) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(path));
            JSONObject station = (JSONObject) jsonData.get(GET_STATIONS);

            for (int i = 0; i < Parser.getLinesCount(); i++) {
                JSONArray stations = (JSONArray) station.get(Parser.getNumerationLines().get(i));
                System.out.printf("Линия: %3s === Количество станций: %s\n",
                        Parser.getNumerationLines().get(i), stations.size());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getJsonFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> stations = Files.readAllLines(Paths.get(path));
            stations.forEach(builder::append);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {
    private static final String REGEX_NUM_LINE = "(?<=ln-)\\w+";
    private static final String REGEX_NAME_LINE = "(?<=>).+(?=<)";
    private static final String REGEX_SPLIT_NAME = "\\s1\\.";
    private static final String REGEX_NUM_STATION = "\\d+\\.";
    private static final String REGEX_NAME_STATION = "\\s{2}";
    private static final String REPLACE = "";
    private static final String QUERY_LINE = "div.js-toggle-depend";
    private static final String QUERY_STATION = "span.num, span.name";
    private static final String GET_NUMBER = "number";
    private static final String GET_NAME = "name";
    private static final String GET_LINE = "lines";
    private static final String GET_STATION = "stations";
    private static final JSONObject JSON_OBJECT = new JSONObject();

    private static List<String> numerationLines;
    private static int linesCount = 0;


    private Parser() {
    }

    protected static JSONObject getJsonObject() {
        return JSON_OBJECT;
    }

    protected static List<String> getNumerationLines() {
        return numerationLines;
    }

    protected static int getLinesCount() {
        return linesCount;
    }

    public static void parseHtml(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(QUERY_LINE);
            Elements names = document.select(QUERY_STATION);

            Pattern numberLines = Pattern.compile(REGEX_NUM_LINE);
            Pattern nameLines = Pattern.compile(REGEX_NAME_LINE);

            Matcher matcherNumber = numberLines.matcher(elements.html());
            Matcher matcherName = nameLines.matcher(elements.html());

            JSONArray linesList = new JSONArray();
            JSONObject stationObject = new JSONObject();
            numerationLines = new ArrayList<>();
            String[] nameStations = names.text().split(REGEX_SPLIT_NAME);

            while (matcherNumber.find() && matcherName.find()) {
                JSONObject lineObject = new JSONObject();
                numerationLines.add(linesCount, matcherNumber.group());
                lineObject.put(GET_NUMBER, matcherNumber.group());
                lineObject.put(GET_NAME, matcherName.group());
                linesList.add(lineObject);
                linesCount++;
            }

            for (int i = 0; i < linesCount; i++) {
                JSONArray stationList = new JSONArray();
                Collections.addAll(stationList, nameStations[i]
                        .replaceAll(REGEX_NUM_STATION, REPLACE).trim().split(REGEX_NAME_STATION));
                stationObject.put(numerationLines.get(i), stationList);
            }

            JSON_OBJECT.put(GET_LINE, linesList);
            JSON_OBJECT.put(GET_STATION, stationObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package utils;

import client.Movements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Parser {
    private static final int INDEX_OPERATION_DESCRIPTION = 5;
    private static final int INDEX_INCOME = 6;
    private static final int INDEX_EXPENSE = 7;
    private static final int INDEX_SIZE = 1;
    private static final String COMMA_DELIMITER = ",";
    private static final String REGEX_QUOTES = "\"";
    private static final String EMPTY_STRING = "";

    public static void loadInfoFromFile(String path) {
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(path));
            for (int i = 1; i < fileLines.size(); i++) {
                String[] splitText = fileLines.get(i).split(COMMA_DELIMITER);
                ArrayList<String> columnList = new ArrayList<>();
                for (String str : splitText) {
                    if (isColumnPart(str)) {
                        String lastText = columnList.get(columnList.size() - INDEX_SIZE);
                        columnList.set(columnList.size() - INDEX_SIZE,
                                lastText.replaceAll(REGEX_QUOTES, EMPTY_STRING)
                                        + "."
                                        + str.replaceAll(REGEX_QUOTES, EMPTY_STRING));
                    } else {
                        columnList.add(str);
                    }
                }
                Movements.getMovements().add(new Movements(
                        columnList.get(INDEX_OPERATION_DESCRIPTION),
                        Double.parseDouble(columnList.get(INDEX_INCOME)),
                        Double.parseDouble(columnList.get(INDEX_EXPENSE))));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf(REGEX_QUOTES) ==
                trimText.lastIndexOf(REGEX_QUOTES) && trimText.endsWith(REGEX_QUOTES);
    }
}
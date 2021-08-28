package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderCSV {
    private static final String PATH = "src/main/resources/mongo.csv";
    private final List<List<String>> records = new ArrayList<>();

    public List<List<String>> reader() {
        try (CSVReader csvReader = new CSVReader(new FileReader(PATH))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }
}
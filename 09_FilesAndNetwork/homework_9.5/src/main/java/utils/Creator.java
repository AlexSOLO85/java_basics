package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public final class Creator {

    private Creator() {
    }

    public static void createFile(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Parser.getJsonObject());

            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
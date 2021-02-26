package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public final class Loader {
    private static final String PATH_SAVE = "images/";
    private static final String REGEX_STRING = "\\?";
    private static final String REPLACEMENT_STRING = "";
    private static final int INDEX_STRING = 1;

    private Loader() {
    }

    protected static void downloadImages(List<String> images) {
        System.out.println("Начало скачивания файлов...");

        images.forEach(path -> {
            try (BufferedInputStream bis = new BufferedInputStream(new URL(path).openStream())) {
                Files.createDirectories(Paths.get(PATH_SAVE));
                Files.copy(bis, Paths.get(PATH_SAVE + new File(path).getName()
                        .replaceAll(REGEX_STRING, REPLACEMENT_STRING)), StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("Скачивание файла ---> %s" + System.lineSeparator(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Скачивание файлов завершено!");

        try (Stream<Path> files = Files.list(Paths.get(PATH_SAVE))) {
            long countFiles = files.count();
            System.out.printf("Количество файлов в директории \"%s\" ---> %d"
                    + System.lineSeparator(), PATH_SAVE, countFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Название файлов:");
        images.stream()
                .map(str -> str.split("/"))
                .forEach(strings -> System.out.printf("Файл: ---> %s"
                        + System.lineSeparator(), strings[strings.length - INDEX_STRING].trim()));
    }
}
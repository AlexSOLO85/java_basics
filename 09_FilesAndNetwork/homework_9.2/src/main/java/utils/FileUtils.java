package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtils {
    private FileUtils() {
    }

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        Path sourcePath = Paths.get(sourceDirectory);
        Path destinationPath = Paths.get(destinationDirectory);
        try {
            Files.walkFileTree(sourcePath, new CopyDirectory(sourcePath, destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
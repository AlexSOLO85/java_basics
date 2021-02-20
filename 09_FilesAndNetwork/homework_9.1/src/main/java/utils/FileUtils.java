package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public final class FileUtils {
    private static long FILE_SIZE = 0;
    private static final double CONVERT_BYTE = 1024;
    private static final double GB_SIZE = Math.pow(2, 30);
    private static final double MB_SIZE = Math.pow(2, 20);
    private static final double KB_SIZE = Math.pow(2, 10);
    private static final String FORMAT_SIZE = "0.0";

    private FileUtils() {
    }

    public static long calculateFolderSize(String path) {
        try {
            FILE_SIZE = Files.walk(Path.of((path)))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .mapToLong(File::length)
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printFolderSize(path, FILE_SIZE);
    }

    private static long printFolderSize(String file, long size) {
        String sizeFile;
        double fileSizeInKB = size / CONVERT_BYTE;
        double fileSizeInMB = fileSizeInKB / CONVERT_BYTE;
        double fileSizeInGB = fileSizeInMB / CONVERT_BYTE;
        DecimalFormat df = new DecimalFormat(FORMAT_SIZE);

        if (size > GB_SIZE) {
            sizeFile = df.format(fileSizeInGB) + " Гб";
        } else if (size > MB_SIZE) {
            sizeFile = df.format(fileSizeInMB) + " Мб";
        } else if (size > KB_SIZE) {
            sizeFile = df.format(fileSizeInKB) + " Кб";
        } else {
            sizeFile = df.format(size) + " Байт(а)";
        }

        System.out.printf("Размер папки \"%s\" составляет %s\n", file, sizeFile);

        return size;
    }
}
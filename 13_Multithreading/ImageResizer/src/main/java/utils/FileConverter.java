package utils;

import java.io.File;
import java.util.Objects;

public final class FileConverter {
    private FileConverter() {
    }

    public static void convertFile(String srcFolder, String dstFolder) {
        File srcDir = new File(srcFolder);
        File dstDir = new File(dstFolder);

        boolean wasSuccessful = dstDir.mkdirs();
        if (!wasSuccessful) {
            System.out.printf("Директория по пути: %s была создана ранее!\n",dstDir);
        } else {
            System.out.printf("Создана директория по пути: %s\n",dstDir);
        }

        if (srcDir.exists()) {
            long start = System.currentTimeMillis();
            File[] files = srcDir.listFiles();
            int processorCount = Runtime.getRuntime().availableProcessors();
            int batchSize = (Objects.requireNonNull(files).length / processorCount) + 1;
            int counter = 0;
            File[] temp = new File[batchSize];

            for (File file : files) {
                temp[counter++] = file;
                if (counter == batchSize) {
                    new ImageResizer(temp, dstFolder, start).start();
                    temp = new File[batchSize];
                    counter = 0;
                }
            }
        } else {
            System.out.printf("Нет такой директории по пути: %s", srcFolder);
        }
    }
}
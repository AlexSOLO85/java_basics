package utils;

import java.io.*;
import java.util.Collections;

public final class FilesWriter {
    private static final String SITEMAP_TXT = "src/main/resources/sitemap.txt";
    private static final String DELIMITER = "-";
    private static final int START_OFFSET = 0;

    private FilesWriter() {
    }

    public static void writeSitemapUrl(SiteMap node) {
        int depth = node.getDepth();
        String tabs = String.join(DELIMITER, Collections.nCopies(depth, "\t"));
        appendStringInFile(tabs + node.getUrl() + System.lineSeparator());
        node.getSubLinks().forEach(FilesWriter::writeSitemapUrl);
    }

    private static void appendStringInFile(String data) {
        try (OutputStream outputStream = new FileOutputStream(FilesWriter.SITEMAP_TXT, true)) {
            outputStream.write(data.getBytes(), START_OFFSET, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
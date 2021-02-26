package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class Parser {
    private static final String TAG = "img";
    private static final String ATTRIBUTE_KEY = "abs:src";

    private Parser() {
    }

    public static void getData(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            List<String> elements = document.getElementsByTag(TAG)
                    .stream()
                    .map(element -> element.attr(ATTRIBUTE_KEY))
                    .collect(Collectors.toList());
            Loader.downloadImages(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
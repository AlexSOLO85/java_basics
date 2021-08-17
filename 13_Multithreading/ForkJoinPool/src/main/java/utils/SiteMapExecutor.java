package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveAction;

import static java.lang.Thread.sleep;

public class SiteMapExecutor extends RecursiveAction {
    private final SiteMap url;
    private final SiteMap rootUrl;
    private static final String FILE = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)";
    private static final String CSS_QUERY = "a[href]";
    private static final String ATTRIBUTE_KEY = "abs:href";
    private static final String INTERNAL_ELEMENTS = "#";
    private static final int SLEEP_TIME = 500;
    private static final int TIMEOUT = 100_000;
    private static final CopyOnWriteArraySet<String> ALL_LINKS = new CopyOnWriteArraySet<>();


    public SiteMapExecutor(SiteMap url, SiteMap rootUrl) {
        this.url = url;
        this.rootUrl = rootUrl;
    }


    @Override
    protected void compute() {
        Set<SiteMapExecutor> taskList = new HashSet<>();
        try {
            sleep(SLEEP_TIME);
            Document doc = Jsoup.connect(url.getUrl()).timeout(TIMEOUT).userAgent("Mozilla").get();
            Elements links = doc.select(CSS_QUERY);
            for (Element link : links) {
                String absUrl = link.attr(ATTRIBUTE_KEY);
                if (isCorrected(absUrl)) {
                    url.addSubLinks(new SiteMap(absUrl));
                    ALL_LINKS.add(absUrl);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        for (SiteMap link : url.getSubLinks()) {
            SiteMapExecutor task = new SiteMapExecutor(link, rootUrl);
            task.fork();
            taskList.add(task);
            try {
                Thread.sleep(100);
                System.out.println(task.url.getUrl());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (SiteMapExecutor task : taskList) {
            task.join();
        }
    }

    private boolean isCorrected(String url) {
        return (!url.isEmpty() && url.startsWith(rootUrl.getUrl())
                && !ALL_LINKS.contains(url) && !url.contains(INTERNAL_ELEMENTS)
                && !url.matches(FILE));
    }
}
import input.UserInput;
import utils.FilesWriter;
import utils.SiteMap;
import utils.SiteMapExecutor;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите название сайта: пример - > https://lenta.ru");
        String url = UserInput.getLine();

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Сканируем сайт");

        long start = System.currentTimeMillis();

        SiteMap rootUrl = new SiteMap(url);
        new ForkJoinPool(numThreads).invoke(new SiteMapExecutor(rootUrl, rootUrl));

        System.out.println("Сканирование завершено!");
        System.out.println("Время сканирования " + ((System.currentTimeMillis() - start) / 1000) + " сек.");

        FilesWriter.writeSitemapUrl(rootUrl);
    }
}
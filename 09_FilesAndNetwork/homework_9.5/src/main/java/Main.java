import utils.Creator;
import utils.Parser;
import utils.Reader;

public class Main {
    private static final String SITE_URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String FILE_PATH = "src/main/resources/metro.json";

    public static void main(String[] args) {
        Parser.parseHtml(SITE_URL);
        Creator.createFile(FILE_PATH);
        Reader.readFile(FILE_PATH);
    }
}
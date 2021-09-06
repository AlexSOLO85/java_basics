import utils.parsers.ParserDOM;
import utils.parsers.ParserSAX;

public class Loader {
    private static final String FILE_NAME = "F:\\Voter\\data-18M.xml";
    private static final long BYTE_IN_MEGABYTE = 1_048_576;

    public static void main(String[] args) {
        ParserDOM parserDOM = new ParserDOM();
        long usageMemoryDOMParser = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        parserDOM.parseFileDOM(FILE_NAME);
        usageMemoryDOMParser = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usageMemoryDOMParser;

        ParserSAX parserSAX = new ParserSAX();
        long usageMemorySAXParser = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        parserSAX.parseFileSAX(FILE_NAME);
        usageMemorySAXParser = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usageMemorySAXParser;

        System.out.printf("Использовано памяти при использовании DOMParser: %d Mb" + System.lineSeparator(),
                usageMemoryDOMParser / BYTE_IN_MEGABYTE);
        System.out.printf("Использовано памяти при использовании SAXParser: %d Mb" + System.lineSeparator(),
                usageMemorySAXParser / BYTE_IN_MEGABYTE);
    }
}
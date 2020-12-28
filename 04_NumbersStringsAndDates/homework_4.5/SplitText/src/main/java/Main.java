
public class Main {

    public static void main(String[] args) {
    }

    public static String splitTextInToWords(String text) {
        return text.replaceAll("[\\p{Punct}\\d]+", " ").
                trim().replaceAll("\\s+", System.lineSeparator());
    }
}
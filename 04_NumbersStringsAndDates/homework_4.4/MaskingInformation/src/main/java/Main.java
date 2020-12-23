public class Main {

    public static void main(String[] args) {
        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008> 1234 <5678> 8912", "***");
        System.out.println(safe);
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        String beginText;
        String endText;
        int beginIndex;
        int endIndex;
        String fullString = text;
        boolean isDiamonds = fullString.contains("<") && fullString.contains(">");

        beginIndex = fullString.indexOf('<');
        endIndex = fullString.indexOf('>') + 1;

        if (isDiamonds) {
            beginText = text.substring(0, beginIndex);
            endText = text.substring(endIndex);
            fullString = beginText + placeholder + endText;
            return fullString;
        }
        return text;
    }
}
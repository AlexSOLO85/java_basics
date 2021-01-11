public class Main {
    private static String LINE = "Каждый охотник желает знать, где сидит фазан";
    private static String REGEX_LIME = ",?\\s+";

    public static void main(String[] args) {
        String[] colorWord = LINE.split(REGEX_LIME);
        for (String str : colorWord) {
            System.out.printf("%s ", str);
        }
        System.out.println();
        ReverseArray.reverse(colorWord);
        for (String str : colorWord) {
            System.out.printf("%s ", str);
        }
    }
}

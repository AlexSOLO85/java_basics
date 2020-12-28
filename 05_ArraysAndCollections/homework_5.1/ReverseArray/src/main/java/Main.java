public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] colorWord = line.split(",?\\s+");
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

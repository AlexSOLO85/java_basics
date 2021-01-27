import java.util.*;

public class CoolNumbers {
    private static final int SEED_NUMBER = 1;
    private static final int FINITE_NUMBER = 999;
    private static final int SEED_REGION_NUMBER = 1;
    private static final int FINITE_REGION_NUMBER = 199;
    private static final int QUANTITY_NUMBER = 2_000_000;
    private static final char[] ALLOWED_LETTER = "АВЕКМНОРСТУХ".toCharArray();
    private static final int LETTERS_LENGTH = ALLOWED_LETTER.length;
    private static final Random RANDOM = new Random();

    public static List<String> generateCoolNumbers() {
        List<String> carNumber = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < QUANTITY_NUMBER; i++) {
            sb.append(getRandomLetters())
                    .append(getRandomNumber())
                    .append(getRandomLetters())
                    .append(getRandomLetters())
                    .append(getRandomRegion());
            carNumber.add(sb.toString());
            sb.setLength(0);
        }
        return carNumber;
    }

    private static String getRandomNumber() {
        int randomNumber;
        String str = "";
        for (int i = SEED_NUMBER; i < FINITE_NUMBER; i++) {
            randomNumber = RANDOM.nextInt(999);
            if (randomNumber < 10) {
                str = "00" + randomNumber;
            } else if (randomNumber < 100) {
                str = "0" + randomNumber;
            } else {
                str = String.valueOf(randomNumber);
            }
        }
        return str;
    }

    private static String getRandomRegion() {
        int randomNumberRegion;
        String str = "";
        for (int i = SEED_REGION_NUMBER; i < FINITE_REGION_NUMBER; i++) {
            randomNumberRegion = RANDOM.nextInt(199);
            if (randomNumberRegion < 10) {
                str = "0" + randomNumberRegion;
            } else {
                str = String.valueOf(randomNumberRegion);
            }
        }
        return str;
    }

    private static char getRandomLetters() {
        return ALLOWED_LETTER[RANDOM.nextInt(LETTERS_LENGTH)];
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        long start = System.nanoTime();
        for (String s : list) {
            if (number.equals(s)) {
                long finishTrue = System.nanoTime();
                long elapsedTrue = finishTrue - start;
                System.out.println("Поиск перебором: номер найден, поиск занял " + elapsedTrue + " нс");
                return true;
            }
        }
        long finishTrue = System.nanoTime();
        long elapsedTrue = finishTrue - start;
        System.out.println("Поиск перебором: номер не найден, поиск занял " + elapsedTrue + " нс");
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        Collections.sort(sortedList);
        long start = System.nanoTime();
        int result = Collections.binarySearch(sortedList, number);
        if (result >= 0) {
            long finishTrue = System.nanoTime();
            long elapsedTrue = finishTrue - start;
            System.out.println("Бинарный поиск: номер найден, поиск занял " + elapsedTrue + " нс");
            return true;
        }
        long finishTrue = System.nanoTime();
        long elapsedTrue = finishTrue - start;
        System.out.println("Бинарный поиск: номер не найден, поиск занял " + elapsedTrue + " нс");
        return false;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        long start = System.nanoTime();
        for (String s : hashSet) {
            if (number.contains(s)) {
                long finishTrue = System.nanoTime();
                long elapsedTrue = finishTrue - start;
                System.out.println("Поиск в HashSet: номер найден, поиск занял " + elapsedTrue + " нс");
                return true;
            }
        }
        long finishTrue = System.nanoTime();
        long elapsedTrue = finishTrue - start;
        System.out.println("Поиск в HashSet: номер не найден, поиск занял " + elapsedTrue + " нс");
        return false;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        long start = System.nanoTime();
        for (String s : treeSet) {
            if (number.contains(s)) {
                long finishTrue = System.nanoTime();
                long elapsedTrue = finishTrue - start;
                System.out.println("Поиск в TreeSet: номер найден, поиск занял " + elapsedTrue + " нс");
                return true;
            }
        }
        long finishTrue = System.nanoTime();
        long elapsedTrue = finishTrue - start;
        System.out.println("Поиск в TreeSet: номер не найден, поиск занял " + elapsedTrue + " нс");
        return false;
    }
}
import java.io.*;

public class GenerateNoThreads {
    public void buildNumber() throws IOException {
        long start = System.currentTimeMillis();
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        File file = new File(" ");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (int regionCode = 1; regionCode <= 199; regionCode++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                stringBuilder.append(firstLetter);
                                stringBuilder.append(padNumber(number, 3));
                                stringBuilder.append(secondLetter);
                                stringBuilder.append(thirdLetter);
                                stringBuilder.append(padNumber(regionCode, 2));
                                stringBuilder.append("\n");
                            }
                        }
                    }
                }
                bufferedWriter.write(stringBuilder.toString());
            }
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateWithThreads {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int QUANTITY_REGIONS = 199;
    private static final int QUANTITY_NUMBERS = 1000;
    private static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public void buildNumber() {
        long start = System.currentTimeMillis();
        List<Integer> list =
                IntStream.rangeClosed(1, QUANTITY_REGIONS).boxed().collect(Collectors.toList());

        int groupingByNumbers = QUANTITY_REGIONS / AVAILABLE_PROCESSORS;
        int addGrouping = (list.size() + groupingByNumbers - 1) / groupingByNumbers;

        List<List<Integer>> result = new ArrayList<>(addGrouping);

        int bound = list.size();

        IntStream.range(0, bound).forEach(i -> {
            if (i % groupingByNumbers == 0) {
                result.add(i / groupingByNumbers, new ArrayList<>());
            }
            result.get(i / groupingByNumbers).add(list.get(i));
        });

        result.stream().map(integers -> new Thread(() -> {
            File file = new File(" " + integers + ".txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
                for (Integer integer : integers) {
                    for (int number = 1; number < QUANTITY_NUMBERS; number++) {
                        StringBuilder builder = new StringBuilder();
                        for (char firstLetter : LETTERS) {
                            for (char secondLetter : LETTERS) {
                                for (char thirdLetter : LETTERS) {
                                    builder.append(firstLetter);
                                    builder.append(padNumber(number, 3));
                                    builder.append(secondLetter);
                                    builder.append(thirdLetter);
                                    builder.append(padNumber(integer, 2));
                                    builder.append("\n");
                                }
                            }
                        }
                        try {
                            bufferedWriter.write(builder.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println((System.currentTimeMillis() - start) + " ms");
        })).forEach(Thread::start);
    }

    private String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        IntStream.range(0, padSize).forEach(i -> numberStr.insert(0, '0'));
        return numberStr.toString();
    }
}
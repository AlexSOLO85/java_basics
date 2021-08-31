import java.util.concurrent.atomic.AtomicReference;

public class Concatenation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        AtomicReference<StringBuilder> str = new AtomicReference<>(new StringBuilder());
        str.get().append("some text some text some text".repeat(200_000));

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
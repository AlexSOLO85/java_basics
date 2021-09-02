import java.io.IOException;

public class Loader {
    public static void main(String[] args) throws IOException {
        GenerateWithThreads generateWithThreads = new GenerateWithThreads();
        generateWithThreads.buildNumber();

        GenerateNoThreads generateNoThreads = new GenerateNoThreads();
        generateNoThreads.buildNumber();
    }
}
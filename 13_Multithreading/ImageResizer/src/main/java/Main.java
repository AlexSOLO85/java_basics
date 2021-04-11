import input.UserInput;
import utils.FileConverter;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите путь к исходной папке: ");
        String srcFolder = UserInput.getString();

        System.out.println("Введите путь к папке назначения: ");
        String dstFolder = UserInput.getString();

        FileConverter.convertFile(srcFolder, dstFolder);
    }
}
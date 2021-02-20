import input.UserInput;
import utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь к папке или введите \"exit\" для выхода:");
            String input = UserInput.getLine();
            if (input.equals("exit")) {
                break;
            }
            FileUtils.calculateFolderSize(input);
        }
    }
}
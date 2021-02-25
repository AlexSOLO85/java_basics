import input.UserInput;
import utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь к папке, которую хотите скопировать");
        String inputSource = UserInput.getLine();
        System.out.println("Введите путь к папке, куда хотите скопировать");
        String inputDestination = UserInput.getLine();
        FileUtils.copyFolder(inputSource, inputDestination);
    }
}
import java.util.Scanner;

public class Main {
    private static final String REG_VALID = "^([А-ЯЁ][а-яё]*-?([А-ЯЁ]?[а-яё]+))\\s([А-ЯЁ][а-яё]+[\\s|-]?([А-ЯЁ]?[а-яё]+))\\s([А-ЯЁ][а-яё]+)$";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            if (input.matches(REG_VALID)) {
                String[] str = input.split("\\s+");
                if (str.length == 3) {
                    System.out.println("Фамилия: " + str[0]);
                    System.out.println("Имя: " + str[1]);
                    System.out.println("Отчество: " + str[2]);
                }
                if (str.length == 4) {
                    System.out.println("Фамилия: " + str[0]);
                    System.out.println("Имя: " + str[1] + " " + str[2]);
                    System.out.println("Отчество: " + str[3]);
                }
            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }
}
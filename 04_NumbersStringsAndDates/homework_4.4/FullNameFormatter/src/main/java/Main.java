import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            char[] chars = input.toCharArray();
            int countSpaces = 0;
            int spaceIndex1 = input.indexOf(' ');
            int spaceIndex2 = input.lastIndexOf(' ');
            if (input.equals("0")) {
                break;
            }
            for (char c : chars) {
                if (c == ' ') {
                    countSpaces++;
                }
            }
            if (countSpaces > 0 && countSpaces < 3 && !isNumber(input)) {
                System.out.println("Фамилия: " + input.substring(0, spaceIndex1).trim());
                System.out.println("Имя: " + input.substring(spaceIndex1, spaceIndex2).trim());
                System.out.println("Отчество: " + input.substring(spaceIndex2).trim());
            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }

    private static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
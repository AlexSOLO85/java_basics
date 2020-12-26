import java.util.Scanner;

public class Main {
    private static final String REG_VALUE = "^((8|\\+7|7)[\\-\\s])?(\\(?\\d{3}\\)?[\\-\\s])([\\d\\s\\-]{7,9})$";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String phoneNumber = input.replaceAll("[^0-9]", "").
                    trim().replaceAll("^(8)", "7").trim();
            if (phoneNumber.length() == 10) {
                phoneNumber = "7" + phoneNumber;
            }
            if (input.matches(REG_VALUE)) {
                System.out.println(phoneNumber);
            } else {
                System.out.println("Неверный формат номера");
            }
        }
    }
}

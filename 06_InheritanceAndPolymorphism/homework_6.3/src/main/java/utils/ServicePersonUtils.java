package utils;

public class ServicePersonUtils {

    public static void printResult(double input, double amount) {
        System.out.printf("Счет пополнен на: %.2f" + System.lineSeparator() +
                "Сумма на счете: %.2f" + System.lineSeparator(), input, amount);
    }

    public static boolean isNotNegative(double input) {
        if (input <= 0) {
            System.out.printf("Введена отрицательная сумма или нулевое значение! %.2f" + System.lineSeparator(), input);
            return false;
        }
        return true;
    }

    public static boolean isAmountMoreTake(double input, double amount) {
        if (input > amount) {
            System.out.printf("Недостаточно средств на счете!" + System.lineSeparator() +
                    "Запрашиваемая сумма: %.2f превышает остаток на счете: %.2f" + System.lineSeparator(), input, amount);
            return false;
        }
        return true;
    }
}
package input;

import java.util.Scanner;

public final class UserInputUtils {
    public static double getDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private UserInputUtils() {
    }
}
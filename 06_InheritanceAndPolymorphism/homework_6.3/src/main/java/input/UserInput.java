package input;

import java.util.Scanner;

public final class UserInput {
    public static double getDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private UserInput() {
    }
}
package input;

import java.util.Scanner;

public final class UserInput {
    private UserInput() {
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
import java.util.Scanner;

public class Main {
    private static final String REGEX_LIST_COMMAND = "^[\\p{Upper}]+";
    private static final String REGEX_ADD_COMMAND = "^[\\p{Upper}]+.+";
    private static final String REGEX_SPLIT = "\\s+";
    private static final String ADD_COMMAND = "ADD";
    private static final String LIST_COMMAND = "LIST";
    private static final EmailList emailList = new EmailList();
    private static String command;
    private static String email;

    public static String inputCommand(String[] string) {
        return string[0].trim();
    }

    public static String nameEmail(String[] string, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < string.length; i++) {
            sb.append(string[i]);
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("Введите команду или \"0\" для завершения: ");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split(REGEX_SPLIT);
            if (input.equals("0")) {
                break;
            } else if (input.matches(REGEX_LIST_COMMAND)) {
                command = input.trim();
            } else if (input.matches(REGEX_ADD_COMMAND)) {
                command = inputCommand(commands);
                email = nameEmail(commands, 1);
            } else {
                System.out.println("Такой команды нет.\nДоступные команды: LIST, ADD.");
            }

            switch (command) {
                case (LIST_COMMAND):
                    emailList.getSortedEmails();
                    break;
                case (ADD_COMMAND):
                    emailList.add(email);
                    break;
            }
        }
    }
}
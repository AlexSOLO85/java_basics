import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String LIST_COMMAND = "LIST";
    private static final String ADD_COMMAND = "ADD";
    private static final String EDIT_COMMAND = "EDIT";
    private static final String DELETE_COMMAND = "DELETE";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String REGEX_SPLIT = "\\s+";
    private static final String REGEX_LIST_COMMAND = "^[\\p{Upper}]+";
    private static final String REGEX_DELETE_COMMAND = "^[\\p{Upper}]+\\s+\\d+";
    private static final String REGEX_EDIT_COMMAND = "^[\\p{Upper}]+\\s+\\d+.+";
    private static final String REGEX_ADD_COMMAND = "^[\\p{Upper}]+.+";
    private static String command;
    private static String deed;
    private static int index;
    private static final TodoList todoList = new TodoList();

    public static String inputCommand(String[] string) {
        return string[0].trim();
    }

    public static int indexDeed(String[] string) {
        return Integer.parseInt(string[1].trim());
    }

    public static String nameDeed(String[] string, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < string.length; i++) {
            sb.append(string[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while (!str.equals(EXIT_COMMAND)) {
            System.out.println("Введите команду или \"EXIT\" для завершения: ");
            str = br.readLine();
            String[] commands = str.split(REGEX_SPLIT);
            if (str.matches(REGEX_LIST_COMMAND)) {
                command = str.trim();
                if (command.equals(LIST_COMMAND)) {
                    todoList.getTodos();
                }
            } else if (str.matches(REGEX_DELETE_COMMAND)) {
                command = inputCommand(commands);
                index = indexDeed(commands);
                if (command.equals(DELETE_COMMAND)) {
                    todoList.delete(index);
                }
            } else if (str.matches(REGEX_EDIT_COMMAND)) {
                command = inputCommand(commands);
                index = indexDeed(commands);
                deed = nameDeed(commands, 2);
                if (command.equals(ADD_COMMAND)) {
                    todoList.add(index, deed);
                }
                if (command.equals(EDIT_COMMAND)) {
                    todoList.edit(deed, index);
                }
            } else if (str.matches(REGEX_ADD_COMMAND)) {
                command = inputCommand(commands);
                deed = nameDeed(commands, 1);
                if (command.equals(ADD_COMMAND)) {
                    todoList.add(deed);
                }
            } else {
                System.out.println("Такой команды нет.\nДоступные команды: LIST, ADD, EDIT, DELETE.");
            }
        }
        br.close();
    }
}

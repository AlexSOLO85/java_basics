public class Main {
    private static final String COMMAND_ADD = "ADD";
    private static final String COMMAND_LIST = "LIST";

    public static void main(String[] args) {
        EmailList emailList = new EmailList();
        System.out.println("Введите команду или \"0\" для завершения: ");
        while (true) {
            String userInput = UserInput.getLine();
            if (userInput.equals("0")) {
                break;
            } else if (userInput.startsWith(COMMAND_ADD)) {
                emailList.add(userInput.replaceFirst(COMMAND_ADD, "").trim());
            } else if (userInput.equals(COMMAND_LIST)) {
                emailList.getSortedEmails();
            } else {
                System.out.println("Такой команды нет.\nДоступные команды: LIST, ADD.");
            }
        }
    }
}
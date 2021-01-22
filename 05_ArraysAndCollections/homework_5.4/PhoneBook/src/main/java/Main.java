public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String userInput = UserInput.getLine();
            if (userInput.equals(CommandInput.COMMAND_EXIT.getCommand())) {
                break;
            } else if (userInput.trim().equals(CommandInput.COMMAND_LIST.getCommand())) {
                phoneBook.getAllContacts();
            } else if (userInput.trim().matches(CommandInput.INPUT_NAME.getCommand())) {
                if (phoneBook.getPhonesByName(userInput).contains(userInput)) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.printf("Введите номер телефона для абонента \"%s\":\n", userInput);
                    String phoneContact = UserInput.getLine();
                    phoneBook.addContact(phoneContact, userInput);
                } else {
                    System.out.println(phoneBook.getPhonesByName(userInput));
                }
            } else if (userInput.trim().matches(CommandInput.INPUT_PHONE.getCommand())) {
                if (phoneBook.getNameByPhone(userInput).equals(userInput)) {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.printf("Введите имя абонента для номера \"%s\":\n", userInput);
                    String nameContact = UserInput.getLine();
                    phoneBook.addContact(userInput, nameContact);
                } else {
                    System.out.println(phoneBook.getNameByPhone(userInput));
                }
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}
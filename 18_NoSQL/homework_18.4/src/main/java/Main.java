import input.UserInput;

import static base.MongoBase.*;

public class Main {
    private static final String commandExample = "Примеры команд:"
            + "\n - ДОБАВИТЬ_МАГАЗИН Девяточка"
            + "\n - ДОБАВИТЬ_ТОВАР Вафли 54"
            + "\n - ВЫСТАВИТЬ_ТОВАР Вафли Девяточка"
            + "\n - СТАТИСТИКА_ТОВАРОВ"
            + "\n - EXIT - завершает работу программы";

    public static void main(String[] args) {
        System.out.println(commandExample);

        while (true) {
            System.out.println("Введите команду:");
            String[] input = split(UserInput.getLine().trim());

            String instruction = input[0];
            String object = input.length > 1 ? input[1] : "";

            switch (instruction) {
                case "ДОБАВИТЬ_МАГАЗИН":
                    addStore(object);
                    break;
                case "ДОБАВИТЬ_ТОВАР":
                    addProducts(object);
                    break;
                case "ВЫСТАВИТЬ_ТОВАР":
                    addProductsToStore(object);
                    break;
                case "СТАТИСТИКА_ТОВАРОВ":
                    printStatistic();
                    break;
                case "EXIT":
                    shutdownDB();
                    return;
                default:
                    printError();
            }
        }
    }

    private static void printError() {
        System.out.println("Неверный ввод!");
        System.out.println(commandExample);
    }
}
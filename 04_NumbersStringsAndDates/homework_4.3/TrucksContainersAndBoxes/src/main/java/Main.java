import java.util.Scanner;

public class Main {
    final static int BOXES_IN_CONTAINER = 27;
    final static int CONTAINERS_IN_TRUCK = 12;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int input = Integer.parseInt(boxes);
        int container = 1;
        int truck = 1;
        int capacityTruck = CONTAINERS_IN_TRUCK * BOXES_IN_CONTAINER;

        if (input > 0) {
            System.out.println("Грузовик: " + truck);
            System.out.println("\tКонтейнер: " + container);
            for (int i = 1; i <= input; i++) {
                System.out.println("\t\tЯщик: " + i);
                if (i % capacityTruck == 0 && i != input) {
                    truck++;
                    System.out.println("Грузовик: " + truck);
                }
                if (i % BOXES_IN_CONTAINER == 0 && i != input) {
                    container++;
                    System.out.println("\tКонтейнер: " + container);
                }
            }
            System.out.println("Необходимо:" + System.lineSeparator() + "грузовиков - " + truck + " шт."
                    + System.lineSeparator() + "контейнеров - " + container + " шт.");
        } else {
            System.out.println("Необходимо:" + System.lineSeparator() + "грузовиков - 0 шт."
                    + System.lineSeparator() + "контейнеров - 0 шт.");
        }
    }
}

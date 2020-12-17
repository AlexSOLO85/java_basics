import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int input = Integer.parseInt(boxes);
        int container = 1;
        int truck = 1;
        int fullnessOfContainer = 27;
        int fullnessOfTruck = 12;

        if (input > 0) {
            System.out.println("Грузовик: " + truck);
            System.out.println("\tКонтейнер: " + container);
            for (int i = 1; i <= input; i++) {
                System.out.println("\t\tЯщик: " + i);
                if (i % (fullnessOfTruck * fullnessOfContainer) == 0 && i != input) {
                    truck++;
                    System.out.println("Грузовик: " + truck);
                }
                if (i % fullnessOfContainer == 0 && i != input) {
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

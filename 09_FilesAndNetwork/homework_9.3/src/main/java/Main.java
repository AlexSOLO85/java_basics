import client.Movements;

public class Main {
    private static final String MOVEMENT_CSV = "src/main/resources/movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(MOVEMENT_CSV);
        movements.getExpenseSum();
        movements.getIncomeSum();
        movements.getUniqueExpenseSum();
    }
}
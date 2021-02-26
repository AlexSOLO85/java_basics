package client;

import utils.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Movements {
    private final static String REGEX_SLASH = "\\\\";
    private final static String REGEX_OPERATION_SPLIT = "\\s{3,}";
    private final static String EXPENSE_OPERATION = "\\";
    private final static int INDEX_STRING = 1;

    private static final List<Movements> MOVEMENTS = new ArrayList<>();
    private String operationDescription;
    private double income;
    private double expense;

    public Movements(String operationDescription,
                     double income, double expense) {
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public Movements(String pathMovementsCsv) {
        Parser.loadInfoFromFile(pathMovementsCsv);
    }

    private String getOperationDescription() {
        return operationDescription;
    }

    private double getIncome() {
        return income;
    }

    private double getExpense() {
        return expense;
    }

    public static List<Movements> getMovements() {
        return MOVEMENTS;
    }

    public double getExpenseSum() {
        double sum = MOVEMENTS.stream()
                .mapToDouble(Movements::getExpense)
                .sum();
        System.out.printf("Сумма расходов: %.2f руб.\n", sum);
        System.out.println("===============================");
        return sum;
    }

    public double getIncomeSum() {
        double sum = MOVEMENTS.stream()
                .mapToDouble(Movements::getIncome)
                .sum();
        System.out.printf("Сумма доходов: %.2f руб.\n", sum);
        System.out.println("===============================");
        return sum;
    }

    public static void getUniqueExpenseSum() {
        System.out.println("Суммы расходов по организациям:");
        System.out.println("===============================");
        getAllOperations().forEach(operation -> {
            double sum = MOVEMENTS.stream()
                    .filter(s -> s.getOperationDescription().contains(operation))
                    .mapToDouble(Movements::getExpense).sum();
            String[] strings = operation.split(REGEX_SLASH);
            System.out.printf("%-35s ----> %-15s = %.2f руб.\n", operation,
                    strings[strings.length - INDEX_STRING].trim(), sum);
        });
    }

    private static List<String> getAllOperations() {
        return Movements.MOVEMENTS.stream()
                .map(s -> s.getOperationDescription().split(REGEX_OPERATION_SPLIT))
                .filter(s -> s[INDEX_STRING].contains(EXPENSE_OPERATION))
                .map(s -> s[INDEX_STRING].substring(s[INDEX_STRING].indexOf(EXPENSE_OPERATION)))
                .distinct()
                .collect(Collectors.toList());
    }
}
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome = LocalDate.now();
    private LocalDate month = LocalDate.of(lastIncome.getYear(), lastIncome.getMonth(), lastIncome.getDayOfMonth());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
    private final String formattedString = lastIncome.format(formatter);

    @Override
    public void take(double amountToTake) {
        if (lastIncome.isBefore(month)) {
            super.take(amountToTake);
        } else {
            System.out.println("Операция невозможна! Последнее пополнение: " + formattedString);
        }
    }
}
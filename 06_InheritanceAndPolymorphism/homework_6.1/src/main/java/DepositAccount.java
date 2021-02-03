import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositAccount extends BankAccount {
    private final String FORMAT_DATE = "EEEE, dd MMMM yyyy";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
    private LocalDate lastIncome = LocalDate.now();
    private LocalDate month = LocalDate.of(lastIncome.getYear(), lastIncome.getMonth(), lastIncome.getDayOfMonth());
    private final String formattedString = lastIncome.format(formatter);

    private void setLastIncome(LocalDate lastIncome) {
        this.lastIncome = lastIncome;
    }

    @Override
    public void take(double amountToTake) {
        if (lastIncome.isBefore(month)) {
            super.take(amountToTake);
        } else {
            System.out.println("Операция невозможна! Последнее пополнение: " + formattedString);
        }
    }

    @Override
    public void put(double amountToPut) {
        setLastIncome(LocalDate.now());
        super.put(amountToPut);
    }
}
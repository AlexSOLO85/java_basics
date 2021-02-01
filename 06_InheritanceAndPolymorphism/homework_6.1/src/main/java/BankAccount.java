public class BankAccount {
    private double amount = 0.0;

    private void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public double put(double amountToPut) {
        if (amountToPut < 0) {
            System.out.printf("Введена отрицательная сумма! %.2f" + System.lineSeparator(), amountToPut);
        } else {
            setAmount(getAmount() + amountToPut);
            System.out.printf("Счет пополнен на: %.2f" + System.lineSeparator() +
                    "Сумма на счете: %.2f" + System.lineSeparator(), amountToPut, getAmount());
        }
        return amountToPut;
    }

    public double take(double amountToTake) {
        if (amountToTake < 0) {
            System.out.printf("Введена отрицательная сумма! %.2f" + System.lineSeparator(), amountToTake);
        } else if (amountToTake > getAmount()) {
            System.out.printf("Недостаточно средств на счете!" + System.lineSeparator() +
                    "Запрашиваемая сумма: %.2f превышает остаток на счете: %.2f" + System.lineSeparator(), amountToTake, getAmount());
        } else {
            setAmount(getAmount() - amountToTake);
            System.out.printf("Снято со счета: %.2f" + System.lineSeparator() +
                    "Сумма на счете: %.2f" + System.lineSeparator(), amountToTake, getAmount());
        }
        return amountToTake;
    }

    public boolean send(BankAccount receiver, double amount) {
        if (this.amount > amount) {
            this.amount -= take(amount);
            receiver.amount += receiver.put(amount);
            return true;
        }
        return false;
    }
}
public class CardAccount extends BankAccount {

    @Override
    public double take(double amountToTake) {
        double amountToTakePercent = (amountToTake / 100) * 1;
        super.take(amountToTake + amountToTakePercent);
        System.out.println("Комиссия за снятие: " + amountToTakePercent);
        return amountToTake;
    }
}
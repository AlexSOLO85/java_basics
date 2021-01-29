public class CardAccount extends BankAccount {
    private double amountToTakePercent;

    public double getAmountToTakePercent() {
        return amountToTakePercent;
    }

    @Override
    public void take(double amountToTake) {
        amountToTakePercent = (amountToTake / 100) * 1;
        super.take(amountToTake + amountToTakePercent);
    }
}
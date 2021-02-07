package client;

public abstract class Client {
    private double amount = 0.0;

    protected void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void put(double amountToPut);

    public abstract void take(double amountToTake);

    public abstract void printInfo();
}
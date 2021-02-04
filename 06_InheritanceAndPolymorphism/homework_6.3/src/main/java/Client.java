public abstract class Client {
    private double amount = 0.0;
    ServicePerson service = new ServicePerson();

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void put(double amountToPut);

    public abstract void take(double amountToTake);

    public abstract void printInfo();
}
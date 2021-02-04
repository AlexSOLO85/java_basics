public class PhysicalPerson extends Client {

    @Override
    public void put(double amountToPut) {
        if (service.isNotNegative(amountToPut)) {
            setAmount(getAmount() + amountToPut);
            service.printResult(amountToPut, getAmount());
        }
    }

    @Override
    public void take(double amountToTake) {
        if (service.isNotNegative(amountToTake) &&
                service.isAmountMoreTake(amountToTake, getAmount())) {
            setAmount(getAmount() - amountToTake);
            service.printResult(amountToTake, getAmount());
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Расчетный счет физического лица" + System.lineSeparator() +
                "Условие списания: без комиссии" + System.lineSeparator() +
                "Условие пополнения: без комиссии" + System.lineSeparator() +
                "Баланс: " + getAmount());
    }
}
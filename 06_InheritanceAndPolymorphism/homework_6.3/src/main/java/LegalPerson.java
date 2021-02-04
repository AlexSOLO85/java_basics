public class LegalPerson extends Client {
    private static final double ONE_PERCENT = 0.01;

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
            setAmount(getAmount() - (amountToTake + amountToTake * ONE_PERCENT));
            service.printResult(amountToTake, getAmount());
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Расчетный счет юридического лица" + System.lineSeparator() +
                "Условие списания: с комисией 1% от суммы" + System.lineSeparator() +
                "Условие пополнения: без комиссии" + System.lineSeparator() +
                "Баланс: " + getAmount());
    }
}
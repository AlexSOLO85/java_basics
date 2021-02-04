public class IndividualBusinessman extends Client {
    private static final double THRESHOLD_PUT = 1000;
    private static final double ONE_PERCENT = 0.01;
    private static final double HALF_PERCENT = 0.005;

    @Override
    public void put(double amountToPut) {
        if (service.isNotNegative(amountToPut) && amountToPut < THRESHOLD_PUT) {
            setAmount(getAmount() + (amountToPut - amountToPut * ONE_PERCENT));
        }
        if (service.isNotNegative(amountToPut) && amountToPut >= THRESHOLD_PUT) {
            setAmount(getAmount() + (amountToPut - amountToPut * HALF_PERCENT));
        }
        service.printResult(amountToPut, getAmount());
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
        System.out.println("Расчетный счет индивидуального предпринимателя" + System.lineSeparator() +
                "Условие списания: без комиссии" + System.lineSeparator() +
                "Условия пополнения до 1000 рублей: пополнение с комиссией 1%" + System.lineSeparator() +
                "Условия пополнения больше 1000 рублей: с комиссией 0,5%" + System.lineSeparator() +
                "Баланс: " + getAmount());
    }
}
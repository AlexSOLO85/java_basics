package client;

import utils.ServicePersonUtils;

public class LegalPerson extends Client {
    private static final double ONE_PERCENT = 0.01;

    @Override
    public void put(double amountToPut) {
        if (ServicePersonUtils.isNotNegative(amountToPut)) {
            setAmount(getAmount() + amountToPut);
            ServicePersonUtils.printResult(amountToPut, getAmount());
        }
    }

    @Override
    public void take(double amountToTake) {
        if (ServicePersonUtils.isNotNegative(amountToTake) &&
                ServicePersonUtils.isAmountMoreTake(amountToTake, getAmount())) {
            setAmount(getAmount() - (amountToTake + amountToTake * ONE_PERCENT));
            ServicePersonUtils.printResult(amountToTake, getAmount());
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
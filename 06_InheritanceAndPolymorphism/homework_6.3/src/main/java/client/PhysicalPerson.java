package client;

import utils.ServicePersonUtils;

public class PhysicalPerson extends Client {

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
            setAmount(getAmount() - amountToTake);
            ServicePersonUtils.printResult(amountToTake, getAmount());
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
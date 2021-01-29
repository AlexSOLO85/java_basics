public class OperationAccount {

    public static void getOperationAccount() {
        BankAccount bankAccount = new BankAccount();
        CardAccount cardAccount = new CardAccount();
        DepositAccount depositAccount = new DepositAccount();

        while (true) {
            System.out.println("Введите команду или число 10 для вызова списка команд.");
            double input = UserInput.getDouble();
            if (input == 0.0D) {
                return;
            }
            switch ((int) input) {
                case 1:
                    System.out.println("Сумма на расчетном счете: " + bankAccount.getAmount());
                    System.out.println("Сумма на карточном счете: " + cardAccount.getAmount());
                    System.out.println("Сумма на депозитном счете: " + depositAccount.getAmount());
                    break;
                case 2:
                    System.out.println("Внесение расчетный на счет. Введите сумму:");
                    bankAccount.put(UserInput.getDouble());
                    break;
                case 3:
                    System.out.println("Снятие с расчетного со счета. Введите сумму:");
                    bankAccount.take(UserInput.getDouble());
                    break;
                case 4:
                    System.out.println("Внесение на карточный счет. Введите сумму:");
                    cardAccount.put(UserInput.getDouble());
                    break;
                case 5:
                    System.out.println("Снятие с карточного счета (комиссия за снятие 1%). Введите сумму:");
                    cardAccount.take(UserInput.getDouble());
                    System.out.println("Комиссия составила: " + cardAccount.getAmountToTakePercent());
                    break;
                case 6:
                    System.out.println("Внесение на депозитный счет. Введите сумму:");
                    depositAccount.put(UserInput.getDouble());
                    break;
                case 7:
                    System.out.println("Снятие с депозитного счета. Введите сумму:");
                    depositAccount.take(UserInput.getDouble());
                    break;
                case 8:
                    System.out.println("Перевести с расчетного счета на карточный счет. Введите сумму:");
                    if (bankAccount.send(cardAccount, UserInput.getDouble())) {
                        System.out.println("Транзакция успешна!");
                    } else {
                        System.out.println("Транзакция не удалась!");
                    }
                    System.out.println("Сумма на карточном счете: " + cardAccount.getAmount());
                    System.out.println("Сумма на расчетном счете: " + bankAccount.getAmount());
                    break;
                case 9:
                    System.out.println("Перевести с расчетного счета на депозитный счет. Введите сумму:");
                    if (bankAccount.send(depositAccount, UserInput.getDouble())) {
                        System.out.println("Транзакция успешна!");
                    } else {
                        System.out.println("Транзакция не удалась!");
                    }
                    System.out.println("Сумма на депозитном счете: " + depositAccount.getAmount());
                    System.out.println("Сумма на расчетном счете: " + bankAccount.getAmount());
                    break;
                case 10:
                    System.out.println("Список команд");
                    System.out.println("0 - Завершить работу" + System.lineSeparator() +
                            "1 - Суммы на счетах" + System.lineSeparator() +
                            "2 - Внесение расчетный на счет" + System.lineSeparator() +
                            "3 - Снятие с расчетного со счета" + System.lineSeparator() +
                            "4 - Внесение на карточный счет" + System.lineSeparator() +
                            "5 - Снятие с карточного счета (комиссия за снятие 1%)" + System.lineSeparator() +
                            "6 - Внесение на депозитный счет" + System.lineSeparator() +
                            "7 - Снятие с депозитного счета" + System.lineSeparator() +
                            "8 - Перевести с расчетного счета на карточный счет" + System.lineSeparator() +
                            "9 - Перевести с расчетного счета на депозитный счет" + System.lineSeparator() +
                            "10 - Справка");
                    break;
                default:
                    System.out.println("Нет такой команды!");
            }
        }
    }
}
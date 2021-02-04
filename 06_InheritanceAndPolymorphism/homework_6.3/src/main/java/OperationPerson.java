public class OperationPerson {
    private static final String PUT_SUM = "Введите сумму зачисления: ";
    private static final String TAKE_SUM = "Введите сумму списания: ";

    private static void printPutSum() {
        System.out.println(PUT_SUM);
    }

    private static void printTakeSum() {
        System.out.println(TAKE_SUM);
    }

    public static void getOperationPerson() {

        Client physicalPerson = new PhysicalPerson();
        Client legalPerson = new LegalPerson();
        Client individualBusinessman = new IndividualBusinessman();

        while (true) {
            System.out.println("Введите команду или число 8 для вызова списка команд.");
            double input = UserInput.getDouble();
            if ((int) input == 0) {
                return;
            }
            switch ((int) input) {
                case 1:
                    physicalPerson.printInfo();
                    legalPerson.printInfo();
                    individualBusinessman.printInfo();
                    break;
                case 2:
                    printPutSum();
                    physicalPerson.put(UserInput.getDouble());
                    break;
                case 3:
                    printTakeSum();
                    physicalPerson.take(UserInput.getDouble());
                    break;
                case 4:
                    printPutSum();
                    legalPerson.put(UserInput.getDouble());
                    break;
                case 5:
                    printTakeSum();
                    legalPerson.take(UserInput.getDouble());
                    break;
                case 6:
                    printPutSum();
                    individualBusinessman.put(UserInput.getDouble());
                    break;
                case 7:
                    printTakeSum();
                    individualBusinessman.take(UserInput.getDouble());
                    break;
                case 8:
                    System.out.println("Список команд");
                    System.out.println("0 - Завершить работу" + System.lineSeparator() +
                            "1 - Справка по счетам" + System.lineSeparator() +
                            "2 - Внесение расчетный на счет физического лица" + System.lineSeparator() +
                            "3 - Снятие с расчетного счета физического лица" + System.lineSeparator() +
                            "4 - Внесение на расчетный счет юридического лица" + System.lineSeparator() +
                            "5 - Снятие с расчетного счета юридического лица" + System.lineSeparator() +
                            "6 - Внесение на расчетный счет индивидуального предпринимателя" + System.lineSeparator() +
                            "7 - Снятие с расчетного счета индивидуального предпринимателя" + System.lineSeparator() +
                            "8 - Справка");
                    break;
            }
        }
    }
}
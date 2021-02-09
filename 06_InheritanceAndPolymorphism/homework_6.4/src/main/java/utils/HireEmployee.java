package utils;

import company.Company;
import employee.Employee;
import employee.Manager;
import employee.Operator;
import employee.TopManager;

public final class HireEmployee {

    private HireEmployee() {
    }

    public static void hireEmployeesOperator(Company company, int quantity) {
        int count = 0;
        for (int i = 0; i < quantity; i++) {
            Employee operator = new Operator(company);
            company.hire(operator);
            count++;
        }
        System.out.println("Принято операторов: " + count);
    }

    public static void hireEmployeesManager(Company company, int quantity) {
        int count = 0;
        for (int i = 0; i < quantity; i++) {
            Employee manager = new Manager(company);
            company.hire(manager);
            count++;
        }
        System.out.println("Принято менеджеров: " + count);
    }

    public static void hireEmployeesTopManager(Company company, int quantity) {
        int count = 0;
        for (int i = 0; i < quantity; i++) {
            Employee topManager = new TopManager(company);
            company.hire(topManager);
            count++;
        }
        System.out.println("Принято Топ менеджеров: " + count);
    }
}
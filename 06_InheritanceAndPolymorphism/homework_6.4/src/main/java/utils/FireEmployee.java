package utils;

import company.Company;
import employee.Employee;

public final class FireEmployee {

    private FireEmployee() {
    }

    public static void fireHalfEmployee(Company company) {
        for (int i = 0; i < company.countEmployees(); i++) {
            Employee employee = company.getEmployees().get(i);
            company.fire(employee);
        }
        System.out.println("Уволено " + company.countEmployees() + " сотрудников");
    }
}
package company;

import employee.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private final int income;
    private final List<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(List<Employee> employee) {
        employees.addAll(employee);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public int getIncome() {
        return income;
    }

    public Company(int income) {
        this.income = income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        System.out.println("Самые высокие зарплаты: ");
        employees.sort((o1, o2) -> Double.compare(o2.getMonthSalary(), o1.getMonthSalary()));
        return getEmployees(count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        System.out.println("Самые низкие зарплаты: ");
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        return getEmployees(count);
    }

    private List<Employee> getEmployees(int count) {
        if (count < 0 || count > employees.size()) {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + " " + "\t" + employees.get(i));
            }
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + " " + "\t" + employees.get(i));
            }
        }
        return employees;
    }

    public int countEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
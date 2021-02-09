package employee;

import company.Company;
import company.Position;
import utils.FormatSalary;

public class Manager implements Employee {
    private static final double FIX_SALARY = 75_000;
    private static final double MIN_SUM = 115_000;
    private static final double MAX_SUM = 140_000;
    private static final double PERCENT = 0.05;
    private final double salaryForCompany;
    Company company;

    public Manager(Company company) {
        this.company = company;
        this.salaryForCompany = getSum();
    }

    private double getSum() {
        return (Math.random() * MAX_SUM - MIN_SUM) + MIN_SUM;
    }

    @Override
    public double getMonthSalary() {
        return FIX_SALARY + (salaryForCompany * PERCENT);
    }

    @Override
    public String toString() {
        return Position.MANAGER.getNamePosition() + " " + FormatSalary.format(getMonthSalary());
    }
}
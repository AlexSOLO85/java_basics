package employee;

import company.Company;
import company.Position;
import utils.FormatSalary;

public class Operator implements Employee {
    private static final double FIX_SALARY = 40_000;
    private Company company;

    public Operator(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return FIX_SALARY;
    }

    @Override
    public String toString() {
        return Position.OPERATOR.getNamePosition() + " " + FormatSalary.format(getMonthSalary());
    }
}
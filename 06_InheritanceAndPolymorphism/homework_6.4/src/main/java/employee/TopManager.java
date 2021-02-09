package employee;

import company.Company;
import company.Position;
import utils.FormatSalary;

public class TopManager implements Employee {
    private static final double FIX_SALARY = 175_000;
    private static final double PERCENT = 1.5;
    private static final double INCOME_COMPANY = 10_000_000;
    Company company;

    public TopManager(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return FIX_SALARY + company.getIncome() > INCOME_COMPANY ? (FIX_SALARY * PERCENT) : 0;
    }

    @Override
    public String toString() {
        return Position.TOP_MANAGER.getNamePosition() + " " + FormatSalary.format(getMonthSalary());
    }
}
import company.Company;
import utils.FireEmployee;
import utils.HireEmployee;

public class PrintInfo {
    private static final int INCOME_COMPANY = 11_000_000;
    private static final int QUANTITY_OPERATOR = 180;
    private static final int QUANTITY_MANAGER = 80;
    private static final int QUANTITY_TOP_MANAGER = 10;
    private static final int QUANTITY_TOP_SALARY = 15;
    private static final int QUANTITY_LOW_SALARY = 30;

    public static void main(String[] args) {
        Company company = new Company(INCOME_COMPANY);

        HireEmployee.hireEmployeesOperator(company, QUANTITY_OPERATOR);
        HireEmployee.hireEmployeesManager(company, QUANTITY_MANAGER);
        HireEmployee.hireEmployeesTopManager(company, QUANTITY_TOP_MANAGER);

        company.getTopSalaryStaff(QUANTITY_TOP_SALARY);
        company.getLowestSalaryStaff(QUANTITY_LOW_SALARY);

        FireEmployee.fireHalfEmployee(company);

        company.getTopSalaryStaff(QUANTITY_TOP_SALARY);
        company.getLowestSalaryStaff(QUANTITY_LOW_SALARY);
    }
}
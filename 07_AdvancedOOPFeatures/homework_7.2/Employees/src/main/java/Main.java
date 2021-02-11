import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";
    private static final int YEAR = 2017;

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, YEAR);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Optional<Employee> optional = staff.stream().
                filter(e -> LocalDate.ofInstant(e.getWorkStart().toInstant(), ZoneId.systemDefault()).getYear() == year).
                max(Comparator.comparing(Employee::getSalary));
        return optional.orElseGet(optional::get);
    }
}
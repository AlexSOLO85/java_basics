import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {
    private static int DAY_OF_BIRTH = 31;
    private static int MONTH_OF_BIRTH = 12;
    private static int YEAR_OF_BIRTH = 1990;
    private static String FORMAT_DATE = " - dd.MM.yyyy - EE";

    public static void main(String[] args) {
        System.out.println(collectBirthdays(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH));
    }

    public static String collectBirthdays(int year, int month, int day) {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.ENGLISH);
        Calendar birthDate = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
        Calendar currentDate = Calendar.getInstance();
        int number = 0;
        while (birthDate.before(currentDate)) {
            builder.append(number).append(dateFormat.format(birthDate.getTime())).append(System.lineSeparator());
            birthDate.add(Calendar.YEAR, 1);
            number++;
        }
        return builder.toString();
    }
}

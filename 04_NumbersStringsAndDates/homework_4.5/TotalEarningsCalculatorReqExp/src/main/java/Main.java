import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        calculateSalarySum("Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей");
    }

    public static int calculateSalarySum(String text) {
        int countSum = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            countSum += Integer.parseInt(m.group());
        }
        if (countSum > 0) {
            System.out.println("Общая сумма заработка: " + countSum);
        } else {
            System.out.println("Никто ничего не заработал");
        }
        return countSum;
    }
}
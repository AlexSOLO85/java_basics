package utils;

import java.text.DecimalFormat;

public final class FormatSalary {
    private FormatSalary() {
    }

    public static String format(double sum) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 руб");
        return decimalFormat.format(sum);
    }
}
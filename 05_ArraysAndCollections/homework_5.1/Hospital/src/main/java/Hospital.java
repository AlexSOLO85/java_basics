import java.text.DecimalFormat;
import java.util.stream.IntStream;

public class Hospital {
    private static float MIN_TEMP = 32.0f;
    private static float MAX_TEMP = 40.0f;
    private static float MIN_TEMP_PATIENTS = 36.2f;
    private static float MAX_TEMP_PATIENTS = 36.9f;
    private static String FORMAT_TEMP = "0.00°C";

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float[] temperatureData = new float[patientsCount];
        for (int i = 0; i < temperatureData.length; i++) {
            temperatureData[i] = generateRandomTemperature(MIN_TEMP, MAX_TEMP);
        }
        return temperatureData;
    }

    public static String getReport(float[] temperatureData) {
        float sumTemperatures = 0f;
        int healthyPatients = 0;
        float[] patientsTemperatures = generatePatientsTemperatures(temperatureData.length);

        for (float temperature : patientsTemperatures) {
            sumTemperatures += temperature;
            if (temperature >= MIN_TEMP_PATIENTS && temperature <= MAX_TEMP_PATIENTS) {
                healthyPatients++;
            }
        }

        String report =
                "Температуры пациентов: " + printArray(patientsTemperatures) +
                        "\nСредняя температура: " + getAverageTemperatures(sumTemperatures, patientsTemperatures) +
                        "\nКоличество здоровых: " + healthyPatients;
        return report;
    }

    private static float generateRandomTemperature(float min, float max) {
        return Math.round(((Math.random() * (max - min) + min)) * 10f) / 10f;
    }

    private static String getAverageTemperatures(float sumTemp, float[] patientsTemp) {
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_TEMP);
        double d = sumTemp / patientsTemp.length;
        return decimalFormat.format(d);
    }

    private static String printArray(float[] array) {
        return String.join(" ",
                IntStream.range(0, array.length)
                        .mapToObj(i -> String.valueOf(array[i])).toArray(String[]::new));
    }
}
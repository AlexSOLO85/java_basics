import java.util.Arrays;

public class Hospital {
    private static float MIN_TEMP = 32.0f;
    private static float MAX_TEMP = 40.0f;

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
            if (temperature >= 36.2f && temperature <= 36.9f) {
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

    private static float getAverageTemperatures(float sumTemp, float[] patientsTemp) {
        return (float) Math.round((sumTemp / patientsTemp.length) * 100f) / 100f;
    }

    private static String printArray(float[] array) {
        return Arrays.toString(array).replaceAll("[\\[\\],]", "");
    }
}
public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.abs(Math.PI * Math.pow(radius, 2));
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return Math.abs((4 * Math.PI * Math.pow(radius, 3)) / 3);
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        return (a + b > c && b + c > a && a + c > b);
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (isTrianglePossible(a, b, c)) {
            double p = (a + b + c) / 2.0;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else
            return -1.0;
    }
}

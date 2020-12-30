public class TwoDimensionalArray {
    public static char SYMBOL = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        char[][] chars = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == j) || (i + j == size - 1)) {
                    chars[i][j] = SYMBOL;
                } else {
                    chars[i][j] = ' ';
                }
            }
            System.out.println((chars[i]));
        }
        return chars;
    }
}
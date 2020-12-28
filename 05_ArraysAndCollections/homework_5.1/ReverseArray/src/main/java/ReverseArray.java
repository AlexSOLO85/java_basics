public class ReverseArray {

    public static String[] reverse (String[] strings){
        int array = strings.length;
        for (int i = 0; i < array / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[array - 1 - i];
            strings[array - 1 - i] = temp;
        }
        return strings;
    }
}

public class Main {
    public static void main(String[] args) {
        String englishAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        englishAlphabetToCode(englishAlphabet);
        russianAlphabetToCode(russianAlphabet);
    }

    private static void englishAlphabetToCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println("Буква английского алфавита: " + c + " Код буквы: " + (int) c);
        }
    }

    private static void russianAlphabetToCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println("Буква русского алфавита: " + c + " Код буквы: " + (int) c);
        }
    }
}
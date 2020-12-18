public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int num1 = Integer.parseInt(text.substring(15, 19));
        int num2 = Integer.parseInt(text.substring(35, 39));
        int num3 = Integer.parseInt(text.substring(56, 61));
        int sum = num1 + num2 + num3;
        System.out.println(sum);
    }
}
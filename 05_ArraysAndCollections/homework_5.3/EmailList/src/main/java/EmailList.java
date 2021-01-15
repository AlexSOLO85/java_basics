import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

public class EmailList {
    private static final String REGEX_EMAIL = ".+@.+\\..+";
    private static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private final TreeSet<String> treeSet = new TreeSet<>();

    public void add(String email) {
        if (email.matches(REGEX_EMAIL)) {
            treeSet.add(email.toLowerCase(Locale.ROOT));
        } else {
            System.out.println(WRONG_EMAIL_ANSWER);
        }
    }

    public List<String> getSortedEmails() {
        ArrayList<String> arrayList = new ArrayList<>(treeSet);
        if (arrayList.isEmpty()) {
            System.out.println("Список email пустой! Воспользуйтесь командой ADD для добавления.");
        }
        for (String str : arrayList) {
            System.out.println(str.toLowerCase(Locale.ROOT));
        }
        return arrayList;
    }
}
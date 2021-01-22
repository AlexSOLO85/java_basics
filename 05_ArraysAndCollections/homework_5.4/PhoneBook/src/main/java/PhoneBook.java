import java.util.*;

public class PhoneBook {
    private final Map<String, String> treeMap = new TreeMap<>();

    public void addContact(String phone, String name) {
        if (phone.matches(CommandInput.INPUT_PHONE.getCommand())
                && name.matches(CommandInput.INPUT_NAME.getCommand())) {
            treeMap.put(phone, name);
            for (String key : treeMap.keySet()) {
                if (treeMap.get(key).equals(phone)) {
                    treeMap.put(phone, name);
                }
            }
            System.out.println("Контакт сохранен!");
        }
    }

    public String getNameByPhone(String phone) {
        Set<Map.Entry<String, String>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, String> pair : entrySet) {
            if (phone.equals(pair.getKey())) {
                phone = pair.getValue() + " - " + pair.getKey();
            }
        }
        return phone;
    }

    public Set<String> getPhonesByName(String name) {
        Set<String> treeSet = new TreeSet<>();
        Set<Map.Entry<String, String>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, String> pair : entrySet) {
            if (name.equals(pair.getValue())) {
                name = pair.getValue() + " - " + pair.getKey();
            }
        }
        treeSet.add(name);
        return treeSet;
    }

    public Set<String> getAllContacts() {
        Set<String> treeSet = new TreeSet<>();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            String name = entry.getValue() + " - " + entry.getKey();
            treeSet.add(name);
        }
        for (String str : treeSet) {
            System.out.println(str);
        }
        return treeSet;
    }
}
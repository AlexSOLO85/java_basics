import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private static final String REGEX_PHONE = "^(\\+7)\\d{10}$";
    private static final String REGEX_EMAIL = ".+@.+\\..+";
    private static final int LENGTH_INPUT = 4;
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != LENGTH_INPUT) {
            throw new IndexOutOfBoundsException("Неверный формат ввода! Пример ввода:\n" + Main.getAddCommand());
        }
        if (!components[INDEX_PHONE].matches(REGEX_PHONE)) {
            throw new IllegalArgumentException("Неверный формат номера!");
        }
        if (!components[INDEX_EMAIL].matches(REGEX_EMAIL)) {
            throw new IllegalArgumentException("Неверный формат email!");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
import java.util.ArrayList;

public class TodoList {
    ArrayList<String> arrayList = new ArrayList<>();

    public void add(String todo) {
        arrayList.add(todo);
        System.out.printf("Добавлено дело \"%s\"\n", todo);
    }

    public void add(int index, String todo) {
        if (index < arrayList.size()) {
            arrayList.add(index, todo);
            System.out.printf("Добавлено дело \"%s\" с индексом \"%s\"\n", todo, index);
        }
        else {
            arrayList.add(todo);
            System.out.printf("Добавлено дело \"%s\"\n", todo);
        }
    }

    public void edit(String todo, int index) {
        if (index < arrayList.size()) {
            String previousTodo = arrayList.get(index);
            arrayList.set(index, todo);
            System.out.printf("Дело \"%s\" заменено на \"%s\"\n", previousTodo, todo);
        }
    }

    public void delete(int index) {
        if (index < arrayList.size()) {
            System.out.printf("Дело \"%s\" удалено\n", arrayList.remove(index));
        } else {
            System.out.println("Дело с таким номером не существует!");
        }
    }

    public ArrayList<String> getTodos() {
        if (arrayList.isEmpty()) {
            System.out.println("Список дел пустой. Воспользуйтесь командой ADD для добавления дела!");
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(i + " - " + arrayList.get(i));
        }
        return arrayList;
    }
}
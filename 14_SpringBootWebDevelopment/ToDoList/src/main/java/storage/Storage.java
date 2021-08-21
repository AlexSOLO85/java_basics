package storage;

import entity.Task;

import java.util.*;

public class Storage {
    private static int index = 1;
    private static final Map<Integer, Task> storage = new HashMap<>();

    public static List<Task> getTaskAll() {
        return new ArrayList<>(storage.values());
    }

    public static Task getTaskById(int id) {
        Optional<Task> optional = Optional.empty();
        return optional.orElse(storage.get(id));
    }

    public static int addTask(Task task) {
        task.setId(index);
        storage.put(index, task);
        return index++;
    }

    public static void updateTaskById(int id, Task task) {
        if (storage.containsKey(id)) {
            storage.put(id, task);
        }
    }

    public static List<Task> updateTaskAll(List<Task> tasks){
        ArrayList<Task> arrayList = new ArrayList<>();
        for (Task task : tasks) {
            arrayList.add(task);
            updateTaskById(task.getId(), task);
        }
        return arrayList;
    }

    public static boolean deleteTaskById(int id) {
        return storage.remove(id, storage.get(id));
    }

    public static void deleteTaskAll() {
        storage.clear();
    }
}
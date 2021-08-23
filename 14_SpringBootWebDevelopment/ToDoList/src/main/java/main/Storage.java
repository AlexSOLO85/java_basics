package main;

import main.model.Task;

import java.util.*;

public class Storage {
    private static int index = 1;
    private static final Map<Integer, Task> STORAGE = new HashMap<>();

    public static List<Task> getTaskAll() {
        return new ArrayList<>(STORAGE.values());
    }

    public static Task getTaskById(int id) {
        Optional<Task> optional = Optional.empty();
        return optional.orElse(STORAGE.get(id));
    }

    public static int addTask(Task task) {
        task.setId(index);
        STORAGE.put(index, task);
        return index++;
    }

    public static void updateTaskById(int id, Task task) {
        if (STORAGE.containsKey(id)) {
            STORAGE.put(id, task);
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
        return STORAGE.remove(id, STORAGE.get(id));
    }

    public static void deleteTaskAll() {
        STORAGE.clear();
    }
}
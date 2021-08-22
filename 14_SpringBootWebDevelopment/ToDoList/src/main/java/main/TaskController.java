package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getTaskAll() {
        return (List<Task>) getTaskRepository().findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Optional<Task> optionalTask = getTaskRepository().findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public int addTask(Task task) {
        Task newTask = getTaskRepository().save(task);
        return newTask.getId();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, Task taskNew) {
        Optional<Task> optionalTask = getTaskRepository().findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        getTaskRepository().save(taskNew);
        return new ResponseEntity<>(taskNew, HttpStatus.OK);
    }

    @PutMapping("/tasks")
    public List<Task> updateTaskAll(@RequestBody List<Task> tasks) {
        return (List<Task>) getTaskRepository().saveAll(tasks);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable int id) {
        Optional<Task> optionalTask = getTaskRepository().findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        getTaskRepository().deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/tasks")
    public void deleteTaskAll() {
        getTaskRepository().deleteAll();
    }
}
package main;

import entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storage.Storage;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/tasks")
    public List<Task> getTaskAll() {
        return Storage.getTaskAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = Storage.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public int addTask(Task task) {
        return Storage.addTask(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, Task taskNew) {
        Task task = Storage.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.updateTaskById(id, taskNew);
        return new ResponseEntity<>(taskNew, HttpStatus.OK);
    }

    @PutMapping("/tasks")
    public List<Task> updateTaskAll(@RequestBody List<Task> tasks){
        return Storage.updateTaskAll(tasks);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable int id) {
        if (Storage.deleteTaskById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/tasks")
    public void deleteTaskAll() {
        Storage.deleteTaskAll();
    }
}
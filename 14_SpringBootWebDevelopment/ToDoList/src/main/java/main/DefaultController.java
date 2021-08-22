package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    private final TaskRepository taskRepository;

    @Autowired
    public DefaultController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> bookIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task book : bookIterable) {
            tasks.add(book);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }
}
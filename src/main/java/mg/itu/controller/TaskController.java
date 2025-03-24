package mg.itu.controller;

import mg.itu.model.task.Task;
import mg.itu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

   @GetMapping("/all")
    public String getTasks(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int perPage,
                        Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the tasks list");
            return "views/auth/login";
        }

        try {
            List<Task> tasks = taskService.getTasks(accessToken, page, perPage);
            model.addAttribute("tasks", tasks);
            model.addAttribute("currentPage", page);
            return "views/task/liste";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching tasks: " + e.getMessage());
            return "views/auth/login";
        }
    }

}
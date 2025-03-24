package mg.itu.controller;

import mg.itu.model.project.Project;
import mg.itu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    public String getProjects(Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the projects list");
            return "views/auth/login";
        }

        try {
            List<Project> projects = projectService.getProjects(accessToken);
            model.addAttribute("projects", projects);
            return "views/project/liste";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching projects: " + e.getMessage());
            return "views/auth/login";
        }
    }
}
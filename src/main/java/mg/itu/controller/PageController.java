package mg.itu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import mg.itu.model.page.DashboardResponse;
import mg.itu.service.AuthService;

@Controller
@RequestMapping("/api")
public class PageController {

    @Autowired
    private AuthService authService;
    
    @GetMapping("/dashboard")
    public String getDashboard(Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token") ;
  
        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the dashboard");
            return "views/auth/login";
        } 

        try {
            DashboardResponse dashboardData = authService.getDashboardData(accessToken);
            System.out.println("Dashboard data retrieved: " + dashboardData); 
            if (dashboardData != null) {
                model.addAttribute("dashboardData", dashboardData);
                return "views/pages/dashboard";
            }
            model.addAttribute("error", "Unable to fetch dashboard data");
            return "views/auth/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error connecting to API");
            return "views/auth/login";
        }
    }
}